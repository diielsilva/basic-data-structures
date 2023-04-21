package com.diel.dev.data_structures.classes;

public class PeopleBox {
    private final People people;
    private PeopleBox nextBox;

    public PeopleBox(People people) {
        this.people = people;
    }

    public People getPeople() {
        return people;
    }

    public PeopleBox getNextBox() {
        return nextBox;
    }

    public void setNextBox(PeopleBox nextBox) {
        this.nextBox = nextBox;
    }
}
