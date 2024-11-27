package com.pankaj.java.trees.rbtree;

import static com.pankaj.java.trees.rbtree.Color.BLACK;

public class RBTree<T extends Comparable<T>> {
    Node<T> root;

    public void insert(T val) {
        if (root == null) {
            root = new Node(val);
            root.color = BLACK;
            return;
        }
        Node<T> parent = null, tmp = root;
        while (tmp != null) {
            parent = tmp;
            if (val.compareTo(tmp.val) < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        if (val.compareTo(parent.val) < 0) {
            parent.left = new Node(val);
        } else {
            parent.right = new Node(val);
        }
    }
}
