package com.pankaj.java.google;

import com.pankaj.java.Node;
import com.pankaj.java.linkedlist.LNode;

public class SortedListToBST {
    static Node root;
    static LNode head;

    LNode add (int val) {
        if (head == null) {
            return new LNode(val);
        }
        LNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new LNode(val);
        return head;
    }

    public Node sortedListToBST (LNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public Node toBST (LNode head, LNode tail) {
        LNode slow = head;
        LNode fast = head;
        if (head == tail) {
            return null;
        }

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Node root = new Node(slow.data);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);
        return root;
    }

    public void preOrderPrint (Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderPrint(root.left);
            preOrderPrint(root.right);
        }
    }

    public static void main(String[] args) {
        SortedListToBST sb = new SortedListToBST();
        int[] arr = {-10, -3, 0, 5, 9};
        for (int num : arr) {
            head = sb.add(num);
        }
        root = sb.sortedListToBST(head);
        sb.preOrderPrint(root);
    }
}
