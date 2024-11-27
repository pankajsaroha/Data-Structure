package com.pankaj.java.linkedlist;

class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}

public class AddOne
{
    static int carry = 1;

    static Node head;

    static void insert (int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    public static Node addOne(Node head)
    {
        int carry = 1;
        head = reverse (head);
        Node temp = head;
        while (temp.next != null) {
            int sum = temp.data + carry;
            carry = (sum == 10) ? 1 : 0;
            temp.data = sum % 10;
            temp = temp.next;
        }
        if (temp.data != 9 || (temp.data == 9 && carry == 0)) {
            temp.data = temp.data + carry;
        } else {
            Node node = new Node(1);
            temp.data = 0;
            temp.next = node;
            temp = temp.next;
        }
        return reverse(head);
    }

    private static Node reverse (Node head) {
        Node prev = null;
        Node current = head;
        Node nextNode = null;

        while (current != null) {
            nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    private static void display (Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AddOne addOne = new AddOne();
        int arr[] = {9, 9, 9};
        for (int data : arr) {
            addOne.insert(data);
        }

        head = addOne(head);
        addOne.display(head);
    }
}

