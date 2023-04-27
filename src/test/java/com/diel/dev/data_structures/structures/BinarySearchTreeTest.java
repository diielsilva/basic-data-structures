package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.BinaryNode;
import com.diel.dev.data_structures.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree tree;

    @BeforeEach
    void setup() {
        tree = new BinarySearchTree();
    }

    @Test
    void add_AssertValueIsInserted_WhenSuccessful() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        BinaryNode root = tree.getRoot();

        assertEquals(18, root.getRightNode().getValue());
        assertEquals(16, root.getRightNode().getLeftNode().getValue());
        assertEquals(19, root.getRightNode().getRightNode().getValue());
        assertEquals(17, root.getRightNode().getLeftNode().getRightNode().getValue());
        assertEquals(12, root.getLeftNode().getValue());
        assertEquals(14, root.getLeftNode().getRightNode().getValue());
        assertEquals(10, root.getLeftNode().getLeftNode().getValue());
        assertEquals(5, root.getLeftNode().getLeftNode().getLeftNode().getValue());
        assertEquals(6, root.getLeftNode().getLeftNode().getLeftNode().getRightNode().getValue());
        assertEquals(4, root.getLeftNode().getLeftNode().getLeftNode().getLeftNode().getValue());
    }

    @Test
    void add_AssertThrowsException_WhenElementIsAlreadyInserted() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        assertThrows(BusinessException.class, () -> tree.add(4));
    }

    @Test
    void search_AssertValueIsReturned_WhenElementIsFound() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        BinaryNode element = tree.search(4);

        assertEquals(4, element.getValue());

        element = tree.search(17);

        assertEquals(17, element.getValue());

        element = tree.search(15);

        assertEquals(15, element.getValue());

        element = tree.search(19);

        assertEquals(19, element.getValue());
    }

    @Test
    void search_AssertThrowsException_WhenElementIsNotFound() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        assertThrows(BusinessException.class, () -> tree.search(29));
        assertThrows(BusinessException.class, () -> tree.search(1));
    }

    @Test
    void delete_AssertValueIsDeleted_WhenSuccessful() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        tree.delete(15);

        BinaryNode root = tree.getRoot();
        BinaryNode node = tree.search(18);

        assertEquals(16, root.getValue());
        assertEquals(17, node.getLeftNode().getValue());

        tree.delete(19);

        assertNull(node.getRightNode());

        tree.delete(12);

        assertEquals(14, root.getLeftNode().getValue());

        node = tree.search(14);

        assertEquals(10, node.getLeftNode().getValue());

        tree.delete(4);

        node = tree.search(5);

        assertNull(node.getLeftNode());
        assertEquals(6, node.getRightNode().getValue());

    }

    @Test
    void delete_AssertThrowsException_WhenValueIsNotFound() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        assertThrows(BusinessException.class, () -> tree.delete(55));
    }

    @Test
    void preOrder_AssertShowInPreOrder_WhenSuccessful() {
        tree.add(15);
        tree.add(18);
        tree.add(16);
        tree.add(19);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(10);
        tree.add(5);
        tree.add(6);
        tree.add(4);

        String result = tree.showInPreOrder();

        assertEquals("15, 12, 10, 5, 4, 6, 14, 18, 16, 17, 19, ", result);
    }

}