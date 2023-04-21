package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.Task;
import com.diel.dev.data_structures.classes.TaskBox;
import com.diel.dev.data_structures.exceptions.BusinessException;

public class LinkedList {
    private TaskBox initial;

    public void add(String description) {
        if (description == null) {
            throw new BusinessException("Description can't be null");
        }
        TaskBox box = new TaskBox(new Task(description));
        if (isEmpty()) {
            initial = box;
        } else {
            TaskBox aux = initial;
            while (aux.getNextBox() != null) {
                aux = aux.getNextBox();
            }
            box.setPreviousBox(aux);
            aux.setNextBox(box);
        }
    }

    public TaskBox removeElementByTaskDescription(String description) {
        if (description == null) {
            throw new BusinessException("Description can't be null");
        }
        ifIsEmptyThenThrowException();
        TaskBox aux = initial;
        boolean isPresent = false;
        while (aux != null) {
            if (aux.getTask().description().equals(description)) {
                isPresent = true;
                break;
            }
            aux = aux.getNextBox();
        }
        if (!isPresent) {
            throw new BusinessException("Task not found");
        }
        if (aux.equals(initial)) {
            initial = initial.getNextBox();
            if (initial != null) {
                initial.setPreviousBox(null);
            }
        } else {
            aux.getPreviousBox().setNextBox(aux.getNextBox());
            if (aux.getNextBox() != null) {
                aux.getNextBox().setPreviousBox(aux.getPreviousBox());
            }
        }
        return aux;
    }

    public TaskBox getTaskByDescription(String description) {
        if (description == null) {
            throw new BusinessException("Description can't be null");
        }
        ifIsEmptyThenThrowException();
        TaskBox aux = initial;
        while (aux != null) {
            if (aux.getTask().description().equals(description)) {
                return aux;
            }
            aux = aux.getNextBox();
        }
        throw new BusinessException("Task not found");
    }

    public void ifIsEmptyThenThrowException() {
        if (isEmpty()) {
            throw new BusinessException("List is empty");
        }
    }

    public boolean isEmpty() {
        return initial == null;
    }
}
