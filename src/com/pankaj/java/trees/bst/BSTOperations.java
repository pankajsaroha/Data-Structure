package com.pankaj.java.trees.bst;

import com.pankaj.java.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BSTOperations {

    Node root;

    public Node insert(int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.peek();
            queue.remove();
            if (data < temp.data) {
                if (temp.left == null) {
                    temp.left = new Node(data);
                    return root;
                } else {
                    queue.add(temp.left);
                }
            }
            if (data > temp.data) {
                if (temp.right == null) {
                    temp.right = new Node(data);
                    return root;
                } else {
                    queue.add(temp.right);
                }
            }
        }
        return root;
    }

    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
}
