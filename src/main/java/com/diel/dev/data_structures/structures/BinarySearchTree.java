package com.diel.dev.data_structures.structures;

import com.diel.dev.data_structures.classes.BinaryNode;
import com.diel.dev.data_structures.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private BinaryNode root;

    public void add(int value) {
        BinaryNode node = new BinaryNode(value);
        if (isEmpty()) {
            root = node;
        } else {
            BinaryNode aux = root;
            boolean loop = true;
            while (loop) {
                if (value == aux.getValue()) {
                    throw new BusinessException("Value is already inserted");
                }
                if (value < aux.getValue() && aux.getLeftNode() != null) {
                    aux = aux.getLeftNode();
                }
                if (value > aux.getValue() && aux.getRightNode() != null) {
                    aux = aux.getRightNode();
                }
                if (value < aux.getValue() && aux.getLeftNode() == null) {
                    aux.setLeftNode(node);
                    loop = false;
                }
                if (value > aux.getValue() && aux.getRightNode() == null) {
                    aux.setRightNode(node);
                    loop = false;
                }
            }
        }
    }

    public void delete(int value) {
        ifIsEmptyThenThrowException();
        BinaryNode node = search(value);
        if (node.getLeftNode() == null && node.getRightNode() == null) {
            BinaryNode parent = getParent(node.getValue());
            if (parent == null) {
                root = null;
            } else if (parent.getLeftNode() != null && parent.getLeftNode().equals(node)) {
                parent.setLeftNode(null);
            } else if (parent.getRightNode() != null && parent.getRightNode().equals(node)) {
                parent.setRightNode(null);
            }
        } else if (node.getLeftNode() != null && node.getRightNode() == null) {
            BinaryNode parent = getParent(node.getValue());
            if (parent == null) {
                root = node.getLeftNode();
            } else {
                parent.setLeftNode(node.getLeftNode());
            }
        } else if (node.getLeftNode() == null && node.getRightNode() != null) {
            BinaryNode parent = getParent(node.getValue());
            if (parent == null) {
                root = node.getRightNode();
            } else {
                parent.setRightNode(node.getRightNode());
            }
        } else if (node.getLeftNode() != null && node.getRightNode() != null) {
            BinaryNode successor = getSuccessor(node.getValue());
            BinaryNode successorParent = getParent(successor.getValue());
            node.setValue(successor.getValue());
            if (successorParent.getValue() == successor.getValue()) {
                node.setRightNode(null);
            } else if (successorParent.getValue() != successor.getValue() && successor.getRightNode() != null) {
                successorParent.setLeftNode(successor.getRightNode());
            } else if (successorParent.getValue() != successor.getValue() && successor.getRightNode() == null) {
                successorParent.setLeftNode(null);
            }
        }
    }

    public BinaryNode search(int value) {
        ifIsEmptyThenThrowException();
        BinaryNode aux = root;
        while (true) {
            if (value == aux.getValue()) {
                return aux;
            }
            if (value < aux.getValue() && aux.getLeftNode() != null) {
                aux = aux.getLeftNode();
            }
            if (value > aux.getValue() && aux.getRightNode() != null) {
                aux = aux.getRightNode();
            }
            if (value < aux.getValue() && aux.getLeftNode() == null) {
                throw new BusinessException("Value not found");
            }
            if (value > aux.getValue() && aux.getRightNode() == null) {
                throw new BusinessException("Value not found");
            }
        }
    }

    public String showInPreOrder() {
        ifIsEmptyThenThrowException();
        BinaryNode aux = root;
        List<BinaryNode> remaining = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        while (aux != null) {
            builder.append(aux.getValue());
            builder.append(", ");
            if (aux.getRightNode() != null) {
                remaining.add(aux.getRightNode());
            }
            if (aux.getLeftNode() != null) {
                aux = aux.getLeftNode();
            } else if (aux.getLeftNode() == null && !remaining.isEmpty()) {
                int position = remaining.size() - 1;
                aux = remaining.get(position);
                remaining.remove(position);
            } else if (aux.getLeftNode() == null && aux.getRightNode() == null && remaining.isEmpty()) {
                aux = null;
            }
        }
        return builder.toString();
    }


    public BinaryNode getParent(int value) {
        ifIsEmptyThenThrowException();
        BinaryNode aux = root;
        BinaryNode node = null;
        while (aux.getValue() != value) {
            if (value < aux.getValue() && aux.getLeftNode() != null) {
                aux = aux.getLeftNode();
                if (aux.getValue() != value) {
                    node = aux;
                }
            }
            if (value > aux.getValue() && aux.getRightNode() != null) {
                aux = aux.getRightNode();
                if (aux.getValue() != value) {
                    node = aux;
                }
            }
            if (value < aux.getValue() && aux.getLeftNode() == null) {
                return null;
            }
            if (value > aux.getValue() && aux.getRightNode() == null) {
                return null;
            }
        }
        return node;
    }

    public BinaryNode getSuccessor(int value) {
        ifIsEmptyThenThrowException();
        BinaryNode aux = search(value);
        if (aux.getRightNode() != null) {
            aux = aux.getRightNode();
            while (aux.getLeftNode() != null) {
                aux = aux.getLeftNode();
            }
        }
        return aux;
    }

    public BinaryNode getRoot() {
        ifIsEmptyThenThrowException();
        return root;
    }

    public void ifIsEmptyThenThrowException() {
        if (isEmpty()) {
            throw new BusinessException("Tree is empty");
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
