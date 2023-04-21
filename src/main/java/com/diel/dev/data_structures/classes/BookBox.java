package com.diel.dev.data_structures.classes;

public class BookBox {
    private final Book book;
    private BookBox previousBox;

    public BookBox(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public BookBox getPreviousBox() {
        return previousBox;
    }

    public void setPreviousBox(BookBox previousBox) {
        this.previousBox = previousBox;
    }
}
