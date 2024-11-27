package com.pankaj.java.trees;

import com.pankaj.java.Node;
import com.pankaj.java.trees.bst.BSTOperations;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ReversePath {

    /*
    * We have to save data rather than Node in the queue because when we replace the data of node (node.data = queue2.peek().data),
    * it will replace the data of node everywhere the node is present (saved in the queue as well) - queue - [50, 70, 50]
    * While if we are saving data in the queue, when we replace the data of the node (node.data = queue.peek), it won't
    * replace the data in the queue because we haven't saved it as a node. It is integer and won't be changed.
    */

    static Node root;
    static int key;
    static Queue<Integer> queue;
    static Queue<Node> queue2;

    //Right Solution - Saving data in the queue
    static void reverse(Node node) {
        if(node == null) {
            return;
        }

        if(node.data == key) {
            queue.add(node.data);
            node.data = queue.peek();
            queue.remove();
            return;
        } else if (key < node.data) {
            queue.add(node.data);
            reverse(node.left);
            node.data = queue.peek();
            queue.remove();
        } else if (key > node.data) {
            queue.add(node.data);
            reverse(node.right);
            node.data = queue.peek();
            queue.remove();
        }
        return;
    }

    //Wrong solution - Saving Node in queue
    static void reverse2(Node node) {
        if(node == null) {
            return;
        }

        if(node.data == key) {
            queue2.add(node);
            node.data = queue2.peek().data;
            queue2.remove();
            return;
        } else if (key < node.data) {
            queue2.add(node);
            reverse2(node.left);
            node.data = queue2.peek().data;
            queue2.remove();
        } else if (key > node.data) {
            queue2.add(node);
            reverse2(node.right);
            node.data = queue2.peek().data;
            queue2.remove();
        }
        return;
    }

    /*static void reversePath(Node root) {
        if (root == null) {
            return;
        }
        queue.add(root);
        if (root.data == key) {
            root.data = queue.peek().data;
            queue.remove();
            return;
        }
        if (key < root.data) {
            reversePath(root.left);
        } else {
            reversePath(root.right);
        }
        root.data = queue.peek().data;
        queue.remove();
    }*/

    static void inorder(Node root)
    {
        if (root != null)
        {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    //Recursive Approach
    static void reverseTreePath(Node root, int data) {
        Map<Integer, Integer> map = new HashMap<>();
        INT nextpos = new INT();
        nextpos.data = 0;
        reverseTreePathUtil(root, data, map, 0, nextpos);
    }

    private static Node reverseTreePathUtil (Node root, int data, Map<Integer, Integer> map, int level, INT nextpos) {
        if (root == null) {
            return null;
        }

        //Final condition - if node is found
        if (data == root.data) {
            map.put(level, root.data); // store the value in it's level
            root.data = map.get(nextpos.data); // change the root value with the current next element of the map
            nextpos.data++;
            return root;
        }
        map.put(level, root.data);

        Node left, right = null;
        left = reverseTreePathUtil(root.left, data, map, level+1, nextpos);
        if (left == null) {
            right = reverseTreePathUtil(root.right, data, map, level+1, nextpos);
        }

        // if current node is part of the path, then do reversing
        if (left != null || right != null) {
            root.data = map.get(nextpos.data);
            nextpos.data++;
            return left != null ? left : right;
        }

        //return null if no element found
        return null;
    }

    public static void main(String[] args) {
        int arr[] = {50, 30, 70, 20, 40, 60, 80};
        BSTOperations bst = new BSTOperations();
        for(int node : arr) root = bst.insert(node);

        queue = new LinkedList<>();
        key = 80;
        queue2 = new LinkedList<>();

        System.out.println("Before Reversal : ");
        inorder(root);

        //reverse(root);
        reverseTreePath(root, key);

        System.out.println("\nAfter Reversal : ");
        inorder(root);
    }

    static class INT {
        int data;
    }
}
