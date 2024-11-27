package com.pankaj.java.geeks;

public class LinkedListProblems {

    static class Node {
        int data;
        Node next;

        Node (int data) {
            this.data = data;
        }
    }

    static Node head;

    public void add(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public void middle () {
        Node temp1 = head;
        Node temp2 = head;
        while (temp2 != null && temp2.next != null) {
            temp1 = temp1.next;
            temp2 = temp2.next.next;
        }
        System.out.println("Middle: " + temp1.data);
    }

    public void deleteMiddle () {
        Node temp1 = head;
        Node temp2 = head;
        Node prev = head;
        if (head.next == null) head = null;
        while (temp2 != null && temp2.next != null) {
            prev = temp1;
            temp1 = temp1.next;
            temp2 = temp2.next.next;
        }
        prev.next = temp1.next;
        System.out.print("After deleting middle: ");
        print();
    }

    public void reverse () {
        Node prev = null;
        Node current = head;
        Node nextone = current.next;
        while (current != null) {
            nextone = current.next;
            current.next = prev;
            prev = current;
            current = nextone;
        }
        head = prev;
        System.out.print("Reversed list: ");
        print();
    }

    public void rotate (int k) {
        Node current = head;
        for(int i=1; i<k && current != null; i++) {
            current = current.next;
        }
        Node nextOfKth = current.next;

        if (nextOfKth == null || k == 0) {
            System.out.println("Last element. List will be unchanged");
            return;
        }

        current.next = null;
        Node newHead = nextOfKth;

        current = nextOfKth;

        while (current.next != null) {
            current = current.next;
        }

        current.next = head;
        head = newHead;
        print();
    }

    //Remove nodes whose sum is equal to zero
    public void removeCancellableNodes () {
        Node start = head;
        Node end;

        while (start != null) {
            end = start.next;
            int sum = start.data;
            boolean modified = false;

            while (end != null) {
                sum += end.data;
                if (sum == 0) {
                    start = end.next;
                    modified = true;
                    break;
                }
                end = end.next;
            }
            if(!modified) {
                start = start.next;
            }
        }
        print();
    }

    public void print() {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {6, -6, 8, 4, -12, 9, 8, -8};
        LinkedListProblems lp = new LinkedListProblems();
        for (int data: arr) {
            lp.add(data);
        }
        lp.print();
        lp.removeCancellableNodes();
    }
}
