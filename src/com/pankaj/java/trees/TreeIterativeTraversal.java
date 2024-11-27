package com.pankaj.java.trees;

import com.pankaj.java.Node;

import java.util.*;

public class TreeIterativeTraversal {

    static Node root;

    public void insert (int data) {
        Node temp = root;
        if(root == null) {
            root = new Node(data);
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(temp);
            while(!queue.isEmpty()) {
                temp = queue.peek();
                queue.remove();
                if(temp.left == null) {
                    temp.left = new Node(data);
                    break;
                }
                queue.add(temp.left);
                if(temp.right == null) {
                    temp.right = new Node(data);
                    break;
                }
                queue.add(temp.right);
            }
        }
    }

    //Inorder

    public void inOrder() {
        Node temp = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || temp != null) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
    }

    //PreOrder

    public void preOrder () {
        Stack<Node> stack = new Stack<>();
        Node temp = root;
        stack.push(temp);
        while (!stack.isEmpty()) {
            temp = stack.pop();
            System.out.print(temp.data + " ");

            if(temp.right != null) {
                stack.push(temp.right);
            }

            if(temp.left != null) {
                stack.push(temp.left);
            }
        }
    }

    //PostOrder

    public void postOrder () {
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        Node current = root;
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            list.add(current.data);
            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
            }
        }
        Collections.reverse(list);
        list.stream().forEach(x -> System.out.print(x + " "));
    }

    public void zigzag(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.add(root);
        boolean leftToRight = true;
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if(leftToRight) {
                if (node.left != null) {
                    stack2.add(node.left);
                }
                if (node.right != null) {
                    stack2.add(node.right);
                }
            } else {
                if (node.right != null) {
                    stack2.add(node.right);
                }
                if (node.left != null) {
                    stack2.add(node.left);
                }
            }
            System.out.print(node.data + " ");
            if (stack1.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> stack = stack1;
                stack1 = stack2;
                stack2 = stack;
            }
        }
    }

    public static void main(String[] args) {
        TreeIterativeTraversal bt = new TreeIterativeTraversal();
        int arr[] = {8, 10, 18, 20, 15, 26, 30};
        for (int i : arr) {
            bt.insert(i);
        }
        bt.inOrder();
        System.out.println();
        bt.preOrder();
        System.out.println();
        bt.postOrder();
        System.out.println();
        bt.zigzag(root);
    }
}
