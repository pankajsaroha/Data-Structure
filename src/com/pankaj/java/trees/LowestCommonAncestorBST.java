package com.pankaj.java.trees;

import com.pankaj.java.Node;
import com.pankaj.java.trees.bst.BSTOperations;

import java.util.Arrays;

public class LowestCommonAncestorBST {

    static Node root;
    static int path[];
    static int index;

    private static int[] search(Node root, int data) {
        if(root == null) {
            return path;
        }
        if(root.data == data) {
            path[index++] = root.data;
        } else if (root.data > data) {
            path[index++] = root.data;
            search(root.left, data);
        } else {
            path[index++] = root.data;
            search(root.right, data);
        }
        return path;
    }

    public static int LCA(Node root, int data1, int data2) {
        if (root == null) {
            return -1;
        }
        if(data1 < root.data && data2 < root.data) {
            return LCA(root.left, data1, data2);
        } else if (data1 > root.data && data2 > root.data) {
            return LCA(root.right, data1, data2);
        } else if((data1 > root.data && data2 < root.data)
                || (data1 < root.data && data2 > root.data)) {
            return root.data;
        } else if ((data1 == root.data && data2 != root.data)) {
            return data1;
        } else if ((data2 == root.data && data1 != root.data)) {
            return data2;
        }
        return -1;
    }

    public static Node LCAGeeksSolution (Node root, int data1, int data2) {
        if (root == null) {
            return null;
        }
        if (data1 < root.data && data2 < root.data) {
            return LCAGeeksSolution(root.left, data1, data2);
        } else if (data1 > root.data && data2 > root.data) {
            return LCAGeeksSolution(root.right, data1, data2);
        }
        return root;
    }

    public static Node LCAIterative(Node root, int data1, int data2) {
        while (root != null) {
            if(data1 < root.data && data2 < root.data) {
                root = root.left;
            } else if (data1 > root.data && data2 > root.data) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BSTOperations bst = new BSTOperations();
        int arr[] = {20, 8, 22, 4, 12, 10, 14};
        for (int data : arr) {
            root = bst.insert(data);
        }

        path = new int[arr.length];
        search(root, 14);
        System.out.println(Arrays.toString(path));

        System.out.println("1. Lowest common ancestor of 10 and 22: " + LCA(root, 10, 22));
        System.out.println("2. Lowest common ancestor of 8 and 14: " + LCAGeeksSolution(root, 8, 14).data);
        System.out.println("3. Lowest common ancestor of 10 and 14: " + LCAGeeksSolution(root, 10, 14).data);
    }
}
