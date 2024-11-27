package com.pankaj.java.trees.rbtree;

public class Node<T extends Comparable<T>> {
    T val;
    Node left;
    Node right;
    Node parent;
    Color color;

    public Node (T val) {
        this.val = val;
        color = Color.RED;
    }
}
