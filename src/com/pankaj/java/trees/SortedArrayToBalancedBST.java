package com.pankaj.java.trees;

import com.pankaj.java.Node;

public class SortedArrayToBalancedBST {

    /*
    * Check another approach for this problem in SortedLinkedListToBalancedBST
    */

    Node root;

    public Node constructTree(int arr[], int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = constructTree(arr, start, mid-1);
        node.right = constructTree(arr, mid+1, end);

        return node;
    }

    private void inorder(Node root) {
        if(root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    private void preorder(Node root) {
        if(root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        SortedArrayToBalancedBST tree = new SortedArrayToBalancedBST();
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Constructing Tree ...");
        tree.root = tree.constructTree(arr, 0, arr.length-1);
        System.out.println("Constructed Tree: ");
        System.out.print("Inorder: ");
        tree.inorder(tree.root);
        System.out.println();
        System.out.print("Preorder: ");
        tree.preorder(tree.root);
    }
}
