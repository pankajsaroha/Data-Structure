package com.pankaj.java.linkedlist;

public class LinkedList {

    public LNode head;

    void insert (int data) {
        if (head == null) {
            head = new LNode(data);
            return;
        }
        LNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new LNode(data);
    }

    public int length () {
        return len(head);
    }

    private int len (LNode temp) {
        if (temp == null) {
            return 0;
        }
        return len(temp.next) + 1;
    }

    public boolean find (int data) {
        return findNode (head, data);
    }

    private boolean findNode (LNode temp, int data) {
        if (temp == null) {
            return false;
        }
        if (temp.data == data) {
            return true;
        }
        return findNode(temp.next, data);
    }

    public LNode reverse () {
        LNode prev = null;
        LNode current = head;
        LNode nextNode = null;

        while (current != null) {
            nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    private void display () {
        LNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public LNode mid (LNode head) {
        LNode slow = head;
        LNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        int nodes[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        for (int data : nodes) {
            list.insert(data);
        }

        list.display();

        System.out.println("\nLength of the list: " + list.length());

        System.out.println("Middle Element : " + list.mid(list.head).data);

        System.out.println(list.find(6) ? "Node exists" : "No node found.");

        list.head = list.reverse();
        list.display();
    }
}
