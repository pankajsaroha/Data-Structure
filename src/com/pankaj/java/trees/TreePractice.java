package com.pankaj.java.trees;

import com.pankaj.java.Node;

public class TreePractice {

    Node root;

    public Node constructTree (int data) {
        return buildTree(root, data);
    }

    private Node buildTree (Node temp, int data) {
        if (temp == null) {
            return new Node(data);
        }

        if (data < temp.data) {
            temp.left = buildTree(temp.left, data);
        }

        if (data > temp.data) {
            temp.right = buildTree(temp.right, data);
        }

        return temp;
    }

    public void inorder (Node temp) {
        if (temp != null) {
            inorder (temp.left);
            System.out.print(temp.data + " ");
            inorder (temp.right);
        }
    }

    public void preorder (Node temp) {
        if (temp != null) {
            System.out.print(temp.data + " ");
            preorder (temp.left);
            preorder(temp.right);
        }
    }

    public boolean isValidBST(Node root) {
        return helper(root, null, null);
    }

    boolean helper(Node root, Integer min, Integer max) {
        if (root == null)
            return true;

        if ((min != null && root.data <= min) || (max != null && root.data >= max))
            return false;

        return helper(root.left, min, root.data) && helper(root.right, root.data, max);
    }

    public int height (Node root) {
        if (root == null) {
            return -1;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreePractice tree = new TreePractice();
        int pre[] = {10, 5, 1, 7, 40, 50};

        for (int data : pre) {
            tree.root = tree.constructTree(data);
        }

        System.out.print("Tree inorder traversal: ");
        tree.inorder(tree.root);

        tree.root = new Node(5);
        tree.root.left = new Node(4);
        tree.root.right = new Node(6);
        tree.root.right.left = new Node(3);
        tree.root.right.right = new Node(7);

        System.out.println(tree.isValidBST(tree.root));
    }
}
