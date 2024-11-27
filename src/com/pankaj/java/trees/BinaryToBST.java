package com.pankaj.java.trees;

import com.pankaj.java.Node;
import com.pankaj.java.trees.bst.BSTOperations;

import java.util.Arrays;

public class BinaryToBST {

    Node root;

    public void convertToBST(Node root, int inorder[]) {
        if (root == null) {
            return;
        }
        storeInOrder(root, inorder);

        int n = countNodes(root);

        Arrays.sort(inorder);

        arrayToBST(root, inorder);
    }

    static int indexBST = 0;
    private void arrayToBST(Node root, int inorder[]) {
        if(root == null) {
            return;
        }
        arrayToBST(root.left, inorder);
        root.data = inorder[indexBST++];
        arrayToBST(root.right, inorder);
    }

    static int index = 0;
    private void storeInOrder(Node root, int inorder[]) {
        if (root == null) {
            return;
        }
        storeInOrder(root.left, inorder);
        inorder[index++] = root.data;
        storeInOrder(root.right, inorder);
    }

    static int count = 0;
    private int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static void main(String[] args) {
        BinaryToBST bst = new BinaryToBST();
        int arr[] = {8, 10, 18, 20, 15, 26, 30};

        BSTOperations bstOperations = new BSTOperations();
        for (int i : arr) {
            bst.root = bstOperations.insert(i);
        }
        bst.convertToBST(bst.root, arr);
        System.out.print("Constructed Tree: ");
        bstOperations.inorder(bst.root);
    }
}
