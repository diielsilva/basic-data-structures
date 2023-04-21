package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.BookBox;
import com.diel.dev.data_structures.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack stack;

    @BeforeEach
    void setup() {
        stack = new Stack();
    }

    @Test
    void push_AssertValueIsInserted_WhenSuccessful() {
        stack.push("Book 01");
        stack.push("Book 02");
        stack.push("Book 03");

        BookBox top = stack.getTop();

        assertEquals("Book 03", top.getBook().name());
        assertEquals("Book 02", top.getPreviousBox().getBook().name());
        assertEquals("Book 01", top.getPreviousBox().getPreviousBox().getBook().name());
    }

    @Test
    void pop_AssertValueIsRemoved_WhenSuccessful() {
        stack.push("Book 01");
        stack.push("Book 02");

        BookBox first = stack.pop();
        BookBox second = stack.pop();

        boolean isEmpty = stack.isEmpty();

        assertTrue(isEmpty);
        assertEquals(second, first.getPreviousBox());
        assertNull(second.getPreviousBox());

    }

    @Test
    void pop_AssertThrowsException_WhenStackIsEmpty() {
        assertThrows(BusinessException.class, () -> stack.pop());
    }

}