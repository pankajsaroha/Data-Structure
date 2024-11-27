package com.pankaj.java;

public class Tree {
    //Binary Search Tree
    private Node root = null;

    public Node getRoot () {
        return this.root;
    }

    public void insert (int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node root, int data) {
        Node node = new Node(data);

        if (root == null) {
            root = node;
        } else {
            Node temp = root;
            if (temp.data > data && temp.left == null) {
                temp.left = node;
            } else if (temp.data < data && temp.right == null) {
                temp.right = node;
            } else {
                if(temp.data > data)
                    insertNode(temp.left, data);
                else
                    insertNode(temp.right, data);
            }
        }
        return root;
    }

    public void inOrder (Node temp) {
        if (temp != null) {
            inOrder(temp.left);
            System.out.print(temp.data + " ");
            inOrder(temp.right);
        }
    }
}
