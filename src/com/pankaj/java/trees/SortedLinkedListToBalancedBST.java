package com.pankaj.java.trees;

import com.pankaj.java.Node;

public class SortedLinkedListToBalancedBST {


    /*
     * Check another approach for this problem in SortedArrayToBalancedBST
     */

    static LinkedListNode head;
    static Node root;

    public Node sortedListToBST() {
        int n = countNodesInList();
        return listToBST(n);
    }

    /*
    * Construct left leaf node first, Create root and add left to it, create right leaf and add right to it
    */
    private Node listToBST(int n) {
        if(n <= 0) {
            return null;
        }

        Node left = listToBST(n/2);

        Node root = new Node(head.data);
        root.left = left;
        head = head.next;

        root.right = listToBST(n - n/2 - 1);

        return root;
    }

    private int countNodesInList() {
        LinkedListNode temp = head;
        int count = 0;
        while(temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private void add(int data) {
        LinkedListNode temp = head;

        if(head == null) {
            head = new LinkedListNode(data);
            return;
        }

        while(temp.next != null) {
            temp = temp.next;
        }

        LinkedListNode node = new LinkedListNode(data);
        temp.next = node;
        node.prev = temp;
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
        SortedLinkedListToBalancedBST list = new SortedLinkedListToBalancedBST();
        int listData[] = {1, 2, 3, 4, 5, 6};
        for(int data : listData) {
            list.add(data);
        }

        root = list.sortedListToBST();
        System.out.println("Tree inorder: ");
        list.inorder(root);
        System.out.println("\nTree Preorder: ");
        list.preorder(root);
    }
}

class LinkedListNode {
    public int data;
    public LinkedListNode next;
    public LinkedListNode prev;

    public LinkedListNode(int data) {
        this.data = data;
    }
}
