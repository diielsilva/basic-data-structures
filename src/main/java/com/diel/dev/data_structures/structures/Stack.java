package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.Book;
import com.diel.dev.data_structures.classes.BookBox;
import com.diel.dev.data_structures.exceptions.BusinessException;

public class Stack {
    private BookBox top;

    public void push(String name) {
        if (name == null) {
            throw new BusinessException("Name can't be null");
        }
        BookBox box = new BookBox(new Book(name));
        if (isEmpty()) {
            top = box;
        } else {
            BookBox aux = top;
            top = box;
            top.setPreviousBox(aux);
        }
    }

    public BookBox pop() {
        ifIsEmptyThenThrowException();
        BookBox aux = top;
        top = aux.getPreviousBox();
        return aux;
    }

    public BookBox getTop() {
        ifIsEmptyThenThrowException();
        return top;
    }

    public void ifIsEmptyThenThrowException() {
        if (isEmpty()) {
            throw new BusinessException("Empty stack");
        }
    }

    public boolean isEmpty() {
        return top == null;
    }
}
