package com.pankaj.java.trees.bst;

import com.pankaj.java.Node;
import com.pankaj.java.trees.bst.BSTOperations;

public class BSTtoGreaterSumTree {

    static Node root;
    static int sum;

    static void greaterSumTree(Node root) {
        if (root != null) {
            greaterSumTree(root.right);
            sum = sum + root.data;
            root.data = sum;
            greaterSumTree(root.left);
        }
    }

    public static void main(String[] args) {
        int arr[] = {11, 2, 29, 1, 7, 15, 40, 35};
        BSTOperations bst = new BSTOperations();
        for (int data : arr) {
            root = bst.insert(data);
        }
        bst.inorder(root);
        greaterSumTree(root);
        System.out.print("Greate sum tree : ");
        bst.inorder(root);
    }
}
