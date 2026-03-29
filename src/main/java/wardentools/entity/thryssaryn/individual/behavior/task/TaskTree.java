package wardentools.entity.thryssaryn.individual.behavior.task;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskTree {
    private ThryssarynEntity entity;
    private final Task rootTask;
    private Map<Task, List<Task>> taskMap = new HashMap<>();
    @Nullable private Task currentTask;

    public TaskTree(ThryssarynEntity entity, Task rootTask) {
        this.rootTask = rootTask;
    }

    public void tick() {
        if (currentTask == null) return;

    }
    
    public static @Nullable Task getHighestPriorityTask(List<Task> tasks) {
        return tasks.stream().max(Comparator.comparingInt(Task::getPriority)).orElse(null);
    }

    public void start() {
        this.currentTask = this.rootTask;
        rootTask.start();
    }

    public Task getRootTask() {
        return rootTask;
    }

    public void addChildren(Task parent, Task child) {
        if (taskMap.containsKey(parent)) {
            taskMap.get(parent).add(child);
        } else {
            taskMap.put(parent, List.of(child));
        }
    }

    public void addChildren(Task task, List<Task> children) {
        if (taskMap.containsKey(task)) {
            taskMap.get(task).addAll(children);
        } else {
            taskMap.put(task, children);
        }
    }

    public List<Task> getChildren(Task task) {
        if (taskMap.containsKey(task)) {
            return taskMap.get(task);
        }
        return List.of();
    }

    public @Nullable Task getCurrentTask() {
        return currentTask;
    }
}
