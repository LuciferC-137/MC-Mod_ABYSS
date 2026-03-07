package wardentools.entity.thryssaryn.client.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.debug.ThryssarynField;
import wardentools.entity.thryssaryn.debug.ThryssarynInfoField;
import wardentools.entity.thryssaryn.individual.AbstractThryssaryn;
import wardentools.network.payloads.debug.DebugEditThryssarynToServer;
import wardentools.network.payloads.debug.RequestCommunityDataToServer;

import java.util.*;

/**
 * Debug screen for inspecting and editing a single Thryssaryn entity.
 * Uses a scrollable list with enum-driven read-only and editable fields.
 * Easily extensible: add entries to {@link ThryssarynInfoField} or {@link ThryssarynField}.
 */
@OnlyIn(Dist.CLIENT)
public class ThryssarynDebugScreen extends Screen {
    private static final int LIST_WIDTH = 300;
    private static final int ROW_HEIGHT = 20;
    private static final int WIDGET_WIDTH = 200;
    private static final int TITLE_COLOR = 0xFF55FFFF;
    private static final int SECTION_COLOR = 0xFFFFAA00;
    private static final int LABEL_COLOR = 0xFFAAAAAA;
    private static final int VALUE_COLOR = 0xFFFFFFFF;

    private final int entityId;
    private final Map<ThryssarynField, String> lastSentValues = new HashMap<>();

    public ThryssarynDebugScreen(int entityId) {
        super(Component.literal("Thryssaryn Debug"));
        this.entityId = entityId;
    }

    private AbstractThryssaryn getEntity() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return null;
        Entity e = mc.level.getEntity(entityId);
        return (e instanceof AbstractThryssaryn t) ? t : null;
    }

    @Override
    protected void init() {
        super.init();
        lastSentValues.clear();

        int listLeft = (this.width - LIST_WIDTH) / 2;
        int listTop = 28;
        int listBottom = this.height - 36;

        FieldList fieldList = new FieldList(this.minecraft, LIST_WIDTH, listBottom - listTop, listTop, listLeft);
        this.addRenderableWidget(fieldList);

        // ── Section: Entity Info ──
        fieldList.addEntry(new SectionEntry("----- Entity Info -----"));
        for (ThryssarynInfoField info : ThryssarynInfoField.values()) {
            fieldList.addEntry(new InfoEntry(info));
        }

        // ── Section: Editable Fields ──
        fieldList.addEntry(new SectionEntry("----- Editable Fields -----"));
        for (ThryssarynField field : ThryssarynField.values()) {
            fieldList.addEntry(createEditableEntry(field));
        }

        // ── Button: View Community ──
        AbstractThryssaryn entity = getEntity();
        if (entity != null && entity.getCommunityId().isPresent()) {
            UUID communityId = entity.getCommunityId().get();
            fieldList.addEntry(new ButtonEntry(
                    Button.builder(Component.literal("View Community"), btn ->
                            PacketDistributor.sendToServer(RequestCommunityDataToServer.fromUuid(communityId))
                    ).width(LIST_WIDTH - 20).build()
            ));
        }

        // Close button at bottom
        this.addRenderableWidget(Button.builder(
                CommonComponents.GUI_DONE, btn -> this.onClose()
        ).bounds(listLeft, this.height - 30, LIST_WIDTH, 20).build());
    }

    private FieldListEntry createEditableEntry(ThryssarynField field) {
        return switch (field.getFieldType()) {
            case BOOLEAN -> new BooleanEditEntry(field);
            case INT -> new TextEditEntry(field);
            case COLOR -> new ColorEditEntry(field);
        };
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(graphics);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        // Title
        AbstractThryssaryn entity = getEntity();
        String title = "Thryssaryn Debug" + (entity != null ? " [ID: " + entityId + "]" : " [Entity not found]");
        graphics.drawCenteredString(this.font, title, this.width / 2, 10, TITLE_COLOR);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // Scroll list and entry classes

    private static class FieldList extends ContainerObjectSelectionList<FieldListEntry> {
        public FieldList(Minecraft mc, int width, int height, int top, int left) {
            super(mc, width, height, top, ROW_HEIGHT);
            this.setX(left);
        }

        @Override
        public int getRowWidth() {
            return LIST_WIDTH - 12;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.getX() + LIST_WIDTH - 6;
        }

        public int addEntry(@NotNull FieldListEntry entry) {
            return super.addEntry(entry);
        }
    }


    private abstract static class FieldListEntry extends ContainerObjectSelectionList.Entry<FieldListEntry> {
    }


    private class SectionEntry extends FieldListEntry {
        private final String text;

        SectionEntry(String text) {
            this.text = text;
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of();
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of();
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            graphics.drawCenteredString(font, text, left + width / 2, top + 6, SECTION_COLOR);
        }
    }

    // ─────────────────── Read-Only Info Entry ───────────────────

    private class InfoEntry extends FieldListEntry {
        private final ThryssarynInfoField infoField;

        InfoEntry(ThryssarynInfoField infoField) {
            this.infoField = infoField;
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of();
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of();
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            AbstractThryssaryn entity = getEntity();
            String label = infoField.getDisplayName() + ":";
            graphics.drawString(font, label, left + 4, top + 6, LABEL_COLOR, false);
            String value = entity != null ? infoField.getValue(entity) : "?";
            graphics.drawString(font, value, left + width - WIDGET_WIDTH, top + 6, VALUE_COLOR, false);
        }
    }

    // ─────────────────── Boolean Toggle Entry ───────────────────

    private class BooleanEditEntry extends FieldListEntry {
        private final ThryssarynField field;
        private final CycleButton<Boolean> toggle;
        /** Value we last sent to server, null if no pending change. */
        private Boolean pendingSentValue = null;

        BooleanEditEntry(ThryssarynField field) {
            this.field = field;
            AbstractThryssaryn entity = getEntity();
            boolean initial = entity != null && field.getValue(entity) != 0;
            this.toggle = CycleButton.booleanBuilder(
                    Component.literal("ON").withStyle(net.minecraft.ChatFormatting.GREEN),
                    Component.literal("OFF").withStyle(net.minecraft.ChatFormatting.RED)
            ).withInitialValue(initial).create(
                    0, 0, WIDGET_WIDTH, 20,
                    Component.literal(field.getDisplayName()),
                    (btn, val) -> {
                        pendingSentValue = val;
                        sendFieldValue(field, val ? 1 : 0);
                    }
            );
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(toggle);
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of(toggle);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            graphics.drawString(font, field.getDisplayName() + ":", left + 4, top + 6, LABEL_COLOR, false);
            toggle.setX(left + width - WIDGET_WIDTH);
            toggle.setY(top);
            toggle.render(graphics, mouseX, mouseY, partialTick);

            // Sync toggle with live entity value only once the server has acknowledged the change
            AbstractThryssaryn entity = getEntity();
            if (entity != null) {
                boolean liveValue = field.getValue(entity) != 0;
                if (pendingSentValue != null && liveValue == pendingSentValue) {
                    pendingSentValue = null; // Server caught up, clear pending
                }
                if (pendingSentValue == null && toggle.getValue() != liveValue) {
                    toggle.setValue(liveValue);
                }
            }
        }
    }

    // ─────────────────── Text/Int Edit Entry ───────────────────

    private class TextEditEntry extends FieldListEntry {
        private final ThryssarynField field;
        private final EditBox editBox;

        TextEditEntry(ThryssarynField field) {
            this.field = field;
            this.editBox = new EditBox(font, 0, 0, WIDGET_WIDTH, 16,
                    Component.literal(field.getDisplayName()));
            editBox.setMaxLength(20);
            AbstractThryssaryn entity = getEntity();
            if (entity != null) {
                editBox.setValue(String.valueOf(field.getValue(entity)));
            }
            editBox.setResponder(text -> onTextEdited(field, text));
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(editBox);
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of(editBox);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            graphics.drawString(font, field.getDisplayName() + ":", left + 4, top + 6, LABEL_COLOR, false);
            editBox.setX(left + width - WIDGET_WIDTH);
            editBox.setY(top + 2);
            editBox.render(graphics, mouseX, mouseY, partialTick);

            if (!editBox.isFocused()) {
                AbstractThryssaryn entity = getEntity();
                if (entity != null) {
                    editBox.setValue(String.valueOf(field.getValue(entity)));
                }
            }
        }
    }

    // Color Edit

    private class ColorEditEntry extends FieldListEntry {
        private final ThryssarynField field;
        private final EditBox editBox;

        ColorEditEntry(ThryssarynField field) {
            this.field = field;
            this.editBox = new EditBox(font, 0, 0, WIDGET_WIDTH - 20, 16,
                    Component.literal(field.getDisplayName()));
            editBox.setMaxLength(6);
            AbstractThryssaryn entity = getEntity();
            if (entity != null) {
                editBox.setValue(String.format("%06X", field.getValue(entity) & 0xFFFFFF));
            }
            editBox.setResponder(text -> onColorEdited(field, text));
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(editBox);
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of(editBox);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            graphics.drawString(font, field.getDisplayName() + ":", left + 4, top + 6, LABEL_COLOR, false);

            int boxX = left + width - WIDGET_WIDTH;
            editBox.setX(boxX + 20);
            editBox.setY(top + 2);
            editBox.render(graphics, mouseX, mouseY, partialTick);

            // Color preview square
            AbstractThryssaryn entity = getEntity();
            int color = entity != null ? field.getValue(entity) : 0;
            int previewX = boxX + 2;
            int previewY = top + 3;
            graphics.fill(previewX - 1, previewY - 1, previewX + 15, previewY + 15, 0xFFFFFFFF);
            graphics.fill(previewX, previewY, previewX + 14, previewY + 14, 0xFF000000 | color);

            if (!editBox.isFocused() && entity != null) {
                editBox.setValue(String.format("%06X", field.getValue(entity) & 0xFFFFFF));
            }
        }
    }

    // Button Entry

    private static class ButtonEntry extends FieldListEntry {
        private final Button button;

        ButtonEntry(Button button) {
            this.button = button;
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(button);
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of(button);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int index, int top, int left,
                           int width, int height, int mouseX, int mouseY,
                           boolean hovering, float partialTick) {
            button.setX(left + (width - button.getWidth()) / 2);
            button.setY(top);
            button.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    // Network sync helper

    private void sendFieldValue(ThryssarynField field, int value) {
        PacketDistributor.sendToServer(
                new DebugEditThryssarynToServer(entityId, field.getFieldId(), value)
        );
    }

    private void onTextEdited(ThryssarynField field, String text) {
        if (text.equals(lastSentValues.get(field))) return;
        try {
            int value = Integer.parseInt(text);
            lastSentValues.put(field, text);
            sendFieldValue(field, value);
        } catch (NumberFormatException ignored) {
        }
    }

    private void onColorEdited(ThryssarynField field, String text) {
        if (text.equals(lastSentValues.get(field))) return;
        try {
            int value = Integer.parseInt(text.replace("#", "").replace("0x", ""), 16);
            lastSentValues.put(field, text);
            sendFieldValue(field, value);
        } catch (NumberFormatException ignored) {
        }
    }
}
