package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.People;
import com.diel.dev.data_structures.classes.PeopleBox;
import com.diel.dev.data_structures.exceptions.BusinessException;

public class Queue {
    private PeopleBox first;
    private PeopleBox last;

    public void push(String name) {
        if (name == null) {
            throw new BusinessException("Name can't be null");
        }
        PeopleBox box = new PeopleBox(new People(name));
        last = box;
        if (isEmpty()) {
            first = box;
        } else {
            PeopleBox aux = first;
            while (aux.getNextBox() != null) {
                aux = aux.getNextBox();
            }
            aux.setNextBox(last);
        }
    }

    public PeopleBox pop() {
        ifIsEmptyThenThrowException();
        PeopleBox aux = first;
        if (first.equals(last)) {
            first = null;
            last = null;
        } else {
            first = first.getNextBox();
        }
        return aux;
    }

    public PeopleBox getFirst() {
        ifIsEmptyThenThrowException();
        return first;
    }

    public PeopleBox getLast() {
        ifIsEmptyThenThrowException();
        return last;
    }

    public void ifIsEmptyThenThrowException() {
        if (isEmpty()) {
            throw new BusinessException("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
