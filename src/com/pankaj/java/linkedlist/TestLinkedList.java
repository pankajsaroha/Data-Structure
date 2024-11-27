package com.pankaj.java.linkedlist;

import java.util.*;

public class TestLinkedList {

    LNode head;

    public LNode reverseBetween(LNode head, int left, int right) {
        if (head == null) return head;

        LNode fast = head;
        LNode slow = head;
        LNode prev = null;

        while (right-- > 1) {
            if (left-- > 1) {
                prev = slow;
                slow = slow.next;
            }
            fast = fast.next;
        }

        LNode cur = slow;
        LNode nxt = null;
        LNode store = prev;
        store.next = null;
        LNode last = fast.next;
        while (cur != last) {
            nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        slow.next = last;
        store.next = fast;
        return head;
    }

    public void display () {
        LNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void reorderList(LNode head) {
        if (head == null || head.next == null) return;
        Stack<LNode> stack = new Stack<>();

        LNode fast = head;
        LNode slow = head;
        LNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        slow = head;
        LNode temp;
        while (slow != null) {
            prev = slow;
            temp = stack.pop();
            temp.next = slow.next;
            slow.next = temp;
            slow = slow.next.next;
        }

        if (!stack.isEmpty()) {
            prev.next.next = stack.pop();
            prev.next.next.next = null;
        }
    }

    public static void main(String[] args) {
        TestLinkedList t = new TestLinkedList();
        int arr[] = {1, 2, 3, 4};
        Arrays.copyOf(arr, 5);
        LinkedList list = new LinkedList();
        for (int data : arr) {
            list.insert(data);
        }
        t.head = list.head;
        //t.reverseBetween(t.head, 2, 4);
        t.reorderList(t.head);
        t.display();
        Set<Integer > set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(1));
    }
}
