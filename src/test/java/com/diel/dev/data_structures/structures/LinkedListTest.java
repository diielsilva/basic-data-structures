package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.TaskBox;
import com.diel.dev.data_structures.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private LinkedList list;

    @BeforeEach
    void setup() {
        list = new LinkedList();
    }

    @Test
    void add_AssertValueIsInserted_WhenSuccessful() {
        list.add("Task 01");
        list.add("Task 02");
        list.add("Task 03");
        list.add("Task 04");

        TaskBox firstBox = list.getTaskByDescription("Task 01");
        TaskBox secondBox = firstBox.getNextBox();
        TaskBox thirdBox = secondBox.getNextBox();
        TaskBox fourthBox = thirdBox.getNextBox();

        assertNotNull(firstBox);
        assertEquals(secondBox, firstBox.getNextBox());
        assertEquals(firstBox, secondBox.getPreviousBox());
        assertEquals(thirdBox, secondBox.getNextBox());
        assertEquals(secondBox, thirdBox.getPreviousBox());
        assertEquals(fourthBox, thirdBox.getNextBox());
        assertEquals(thirdBox, fourthBox.getPreviousBox());
    }

    @Test
    void remove_AssertValueIsRemoved_WhenSuccessful() {
        list.add("Task 01");
        list.add("Task 02");
        list.add("Task 03");
        list.add("Task 04");

        TaskBox firstBox = list.getTaskByDescription("Task 01");
        TaskBox secondBox = firstBox.getNextBox();
        TaskBox thirdBox = secondBox.getNextBox();
        TaskBox fourthBox = thirdBox.getNextBox();

        list.removeElementByTaskDescription("Task 03");

        assertEquals(fourthBox, secondBox.getNextBox());
        assertEquals(secondBox, fourthBox.getPreviousBox());

        list.removeElementByTaskDescription("Task 04");

        assertNull(secondBox.getNextBox());
        assertEquals(firstBox, secondBox.getPreviousBox());

        list.removeElementByTaskDescription("Task 01");

        assertNull(secondBox.getNextBox());
        assertNull(secondBox.getPreviousBox());

        list.removeElementByTaskDescription("Task 02");

        boolean isEmpty = list.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    void pop_AssertThrowsException_WhenValueIsNotFound() {
        list.add("Task 01");
        list.add("Task 02");

        assertThrows(BusinessException.class, () -> list.removeElementByTaskDescription("Task 03"));
    }

    @Test
    void getTaskByDescription_AssertValueIsReturned_WhenDescriptionIsFound() {
        list.add("Task 01");
        list.add("Task 02");
        list.add("Task 03");

        TaskBox box = list.getTaskByDescription("Task 01");

        assertNotNull(box);
        assertEquals("Task 01", box.getTask().description());

        box = list.getTaskByDescription("Task 02");

        assertNotNull(box);
        assertEquals("Task 02", box.getTask().description());

        box = list.getTaskByDescription("Task 03");

        assertNotNull(box);
        assertEquals("Task 03", box.getTask().description());

    }

}