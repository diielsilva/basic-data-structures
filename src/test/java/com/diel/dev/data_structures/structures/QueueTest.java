package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.PeopleBox;
import com.diel.dev.data_structures.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue queue;

    @BeforeEach
    void setup() {
        queue = new Queue();
    }

    @Test
    void push_AssertValueIsInserted_WhenSuccessful() {
        queue.push("People 01");

        PeopleBox first = queue.getFirst();
        PeopleBox last = queue.getLast();

        assertEquals(first, last);

        queue.push("People 02");
        queue.push("People 03");
        queue.push("People 04");

        first = queue.getFirst();
        last = queue.getLast();

        assertEquals("People 01", first.getPeople().name());
        assertEquals("People 04", last.getPeople().name());
    }

    @Test
    void pop_AssertValueIsRemoved_WhenSuccessful() {
        queue.push("People 01");
        queue.push("People 02");
        queue.push("People 03");
        queue.push("People 04");

        PeopleBox box = queue.pop();

        assertEquals("People 01", box.getPeople().name());

        box = queue.pop();

        assertEquals("People 02", box.getPeople().name());

        box = queue.pop();

        assertEquals("People 03", box.getPeople().name());

        box = queue.pop();

        assertEquals("People 04", box.getPeople().name());

        boolean isEmpty = queue.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    void pop_AssertThrowsException_WhenQueueIsEmpty() {
        assertThrows(BusinessException.class, () -> queue.pop());
    }

}