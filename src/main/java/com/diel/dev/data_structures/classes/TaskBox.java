package com.diel.dev.data_structures.classes;

public class TaskBox {
    private final Task task;
    private TaskBox previousBox;
    private TaskBox nextBox;

    public TaskBox(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public TaskBox getPreviousBox() {
        return previousBox;
    }

    public void setPreviousBox(TaskBox previousBox) {
        this.previousBox = previousBox;
    }

    public TaskBox getNextBox() {
        return nextBox;
    }

    public void setNextBox(TaskBox nextBox) {
        this.nextBox = nextBox;
    }
}
