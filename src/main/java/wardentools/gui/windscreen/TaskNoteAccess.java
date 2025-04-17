package wardentools.gui.windscreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wardentools.ModMain;
import wardentools.network.PacketHandler;
import wardentools.playerdata.tasks.TaskDataProvider;
import wardentools.playerdata.tasks.TaskDataSyncServerPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TaskNoteAccess {
    public static final int NUMBER_OF_TASKS = 10;
    public static final int HEIGHT_OF_ONE_TASK = 24;
    public static final int LENGTH_OF_ONE_TASK = 42;
    public static final int ICON_TO_BUTTON_MARGIN = 36;
    public static final int Y_TASK_MARGIN = 3;
    public List<List<Task>> sections = new ArrayList<>();
    private static final Map<Integer, Task> tasks = new HashMap<>();
    static {
        tasks.put(0, new Task(0, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
                "textures/gui/wind_journal/deepforest.png")));
        tasks.put(1, new Task(1, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
                "textures/gui/wind_journal/whiteforest.png")));
        tasks.put(2, new Task(2, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
                "textures/gui/wind_journal/protector.png")));
        tasks.put(3, new Task(3));
    }


    public TaskNoteAccess() {
        this.buildHasCompletedTask();
        this.buildSections();
    }

    private void buildSections() {
        int i = 0;
        while (i < tasks.size()) {
            int line = 0;
            List<Task> page = new ArrayList<>();
            while (line < WindJournalScreen.TEXTURE_HEIGHT - 2 * WindJournalScreen.PAGE_TEXT_Y_OFFSET && i
                    < tasks.size()) {
                Task task = tasks.get(i);
                if (task != null) {
                    page.add(task);
                    line+= HEIGHT_OF_ONE_TASK + Y_TASK_MARGIN;
                    i++;
                }
            }
            if (!page.isEmpty()) this.sections.add(page);
        }
    }

    private void buildHasCompletedTask() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        player.getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(data -> {
            for (int i : data.getAll()) {
                Task task = tasks.get(i);
                if (task != null) {task.setOk(true);}
            }
        });
    }

    public List<Task> getTaskPage(int index) {
        if (index < 0 || index >= this.sections.size()) {
            return new ArrayList<>();
        }
        return this.sections.get(index);
    }

    public int getPageCount() {
        return this.sections.size();
    }

    public List<CheckButton> getTaskButtons(int index, int x_left, int y_top) {
        List<CheckButton> buttons = new ArrayList<>();
        if (index < 0 || index >= this.sections.size()) {
            return buttons;
        }
        for (int i = 0; i < this.sections.get(index).size(); i++) {
            Task task = this.sections.get(index).get(i);
            buttons.add(new CheckButton(x_left + LENGTH_OF_ONE_TASK + ICON_TO_BUTTON_MARGIN,
                    y_top - 10 + (HEIGHT_OF_ONE_TASK + Y_TASK_MARGIN) * i, task, task::defaultOnPress));
        }
        return buttons;
    }

    public void updateTaskButtonXPos(CheckButton button, int x_left) {
        button.updateXPos(x_left + LENGTH_OF_ONE_TASK + ICON_TO_BUTTON_MARGIN);
    }

    public static class Task {
        int id;
        private ResourceLocation icon
                = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/default_task.png");
        boolean isOk = false;

        public Task(int id) {
            this.id = id;
        }

        public Task(int id, ResourceLocation icon) {
            this.id = id;
            this.icon = icon;
        }

        public void setOk(boolean isOk) {this.isOk = isOk;}

        public void switchState() {this.isOk = !this.isOk;}

        public Component getHoveredText() {
            return JournalAccess.hoveredText(this.getLangName());
        }

        public ResourceLocation getIcon() {return this.icon;}

        private String getLangName() {return ModMain.MOD_ID + ".wind_task." + this.id;}

        public void defaultOnPress(Button button) {
            this.switchState();
            Player player = Minecraft.getInstance().player;
            if (player == null) return;
            player.getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(completedTasks -> {
                if (!completedTasks.taskCompleted(this.id)) {
                    completedTasks.addCompletedTask(this.id);
                } else {
                    completedTasks.removeCompletedTask(this.id);
                }
                PacketHandler.sendToServer(new TaskDataSyncServerPacket(this.id));
            });
        }
    }

}
