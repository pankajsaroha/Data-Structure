package com.pankaj.java.trees;

import com.pankaj.java.Node;

import java.util.Stack;

public class BinaryTreeFromPreOrder {

    Node root;

    public void constructTree(int data) {
        root = buildTree(root, data);
    }

    private Node buildTree(Node temp, int data) {
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

    public void constructTreeUsingStack(int pre[]) {
        if(pre.length == 0) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        root = new Node(pre[0]);
        stack.push(root);
        for(int i=1; i<pre.length; i++) {
            Node temp = null;
            while(!stack.isEmpty() && pre[i] > stack.peek().data) {
                temp = stack.pop();
            }

            if(temp == null) {
                temp = stack.peek();
                temp.left = new Node(pre[i]);
                stack.push(temp.left);
            } else {
                temp.right = new Node(pre[i]);
                stack.push(temp.right);
            }
        }
    }

    public void inorder(Node root) {
        if(root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void inorderUsingStack() {
        Stack<Node> stack = new Stack<>();
        Node temp = root;
        while(!stack.isEmpty() || temp != null) {
            while(temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
    }

    public static void main(String[] args) {
        BinaryTreeFromPreOrder tree = new BinaryTreeFromPreOrder();
        int pre[] = {10, 5, 1, 7, 40, 50};

        for (int data : pre) {
            tree.constructTree(data);
        }

        //tree.constructTreeUsingStack(pre);
        System.out.print("Tree inorder traversal: ");
        tree.inorderUsingStack();
    }
}
