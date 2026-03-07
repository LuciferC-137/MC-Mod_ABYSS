package wardentools.entity.thryssaryn.client.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.debug.CommunityInfoField;
import wardentools.entity.thryssaryn.individual.AbstractThryssaryn;

import java.util.List;
import java.util.UUID;

/**
 * Debug screen for inspecting a Thryssaryn community.
 * Uses a scrollable list with enum-driven read-only fields and member buttons.
 * Easily extensible: add new fields to {@link CommunityInfoField}.
 */
@OnlyIn(Dist.CLIENT)
public class CommunityDebugScreen extends Screen {
    private static final int LIST_WIDTH = 300;
    private static final int ROW_HEIGHT = 20;
    private static final int WIDGET_WIDTH = 200;
    private static final int TITLE_COLOR = 0xFF55FFFF;
    private static final int SECTION_COLOR = 0xFFFFAA00;
    private static final int LABEL_COLOR = 0xFFAAAAAA;
    private static final int VALUE_COLOR = 0xFFFFFFFF;

    private final CommunityInfoField.CommunitySnapshot snapshot;

    public CommunityDebugScreen(UUID communityUuid, int centerX, int centerY, int centerZ,
                                int radius, List<UUID> memberUuids) {
        super(Component.literal("Community Debug"));
        this.snapshot = new CommunityInfoField.CommunitySnapshot(
                communityUuid, centerX, centerY, centerZ, radius, memberUuids
        );
    }

    @Override
    protected void init() {
        super.init();

        int listLeft = (this.width - LIST_WIDTH) / 2;
        int listTop = 28;
        int listBottom = this.height - 36;

        FieldList fieldList = new FieldList(this.minecraft, LIST_WIDTH, listBottom - listTop, listTop, listLeft);
        this.addRenderableWidget(fieldList);

        // ── Section: Community Info ──
        fieldList.addEntry(new SectionEntry("----- Community Info -----"));
        for (CommunityInfoField info : CommunityInfoField.values()) {
            fieldList.addEntry(new InfoEntry(info));
        }

        // ── Section: Members ──
        fieldList.addEntry(new SectionEntry("----- Members -----"));
        for (UUID memberUuid : snapshot.memberUuids()) {
            fieldList.addEntry(new MemberEntry(memberUuid));
        }

        // Close button at bottom
        this.addRenderableWidget(Button.builder(
                CommonComponents.GUI_DONE, btn -> this.onClose()
        ).bounds(listLeft, this.height - 30, LIST_WIDTH, 20).build());
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(graphics);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, "Community Debug", this.width / 2, 10, TITLE_COLOR);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private Entity findEntityByUuid(UUID uuid) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return null;
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity.getUUID().equals(uuid)) return entity;
        }
        return null;
    }

    // ─────────────────── Scroll List ───────────────────

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

    // ─────────────────── Base Entry ───────────────────

    private abstract static class FieldListEntry extends ContainerObjectSelectionList.Entry<FieldListEntry> {
    }

    // ─────────────────── Section Header ───────────────────

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
        private final CommunityInfoField infoField;

        InfoEntry(CommunityInfoField infoField) {
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
            String label = infoField.getDisplayName() + ":";
            graphics.drawString(font, label, left + 4, top + 6, LABEL_COLOR, false);
            String value = infoField.getValue(snapshot);
            graphics.drawString(font, value, left + width - WIDGET_WIDTH, top + 6, VALUE_COLOR, false);
        }
    }

    // ─────────────────── Member Entry ───────────────────

    private class MemberEntry extends FieldListEntry {
        private final UUID memberUuid;
        private final Button button;

        MemberEntry(UUID memberUuid) {
            this.memberUuid = memberUuid;
            Entity entity = findEntityByUuid(memberUuid);
            boolean isLoaded = entity != null;

            String label;
            if (isLoaded && entity instanceof AbstractThryssaryn t) {
                label = "● " + shortUuid(memberUuid)
                        + " [Age: " + t.getAge() + ", HP: " + String.format("%.0f", t.getHealth()) + "]";
            } else {
                label = "○ " + shortUuid(memberUuid) + " (unloaded)";
            }

            this.button = Button.builder(
                    Component.literal(label),
                    btn -> {
                        Entity e = findEntityByUuid(this.memberUuid);
                        if (e instanceof AbstractThryssaryn) {
                            Minecraft.getInstance().setScreen(new ThryssarynDebugScreen(e.getId()));
                        }
                    }
            ).width(LIST_WIDTH - 20).build();
            this.button.active = isLoaded;
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

    // ─────────────────── Utils ───────────────────

    private static String shortUuid(UUID uuid) {
        return uuid.toString().substring(0, 8) + "...";
    }
}
