package com.pankaj.java.trees.btree;

import java.util.Arrays;

public class BTree {

    //https://github.com/anandbikas/coding/commit/76b99d8e8317e4271f6e8f1f1bdc9c3df1a3ec5f
    private BNode root;

    //BTree degree
    private int t;

    public BTree (int t) {
        this.t = t;
    }

    public BNode findNodeForKey (int key) {
        return findNodeForKey(root, key);
    }

    private BNode findNodeForKey(BNode node, int key) {
        if (node == null) {
            return null;
        }

        int left = 0; int right = node.n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (key == node.keys[mid]) {
                return node;
            }

            if (key < node.keys[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        //If not found
        if (node.isLeaf()) {
            return null;
        }
        return findNodeForKey(node.children[right+1], key);
    }

    public void insert (int key) {

        if (root == null) {
            root = new BNode(t);
            root.insertNodeAsSorted(key);
            return;
        }

        BNode parent = null;
        BNode node = root;
        int childIndex = 0;


        while (true) {
            if (node.isFull()) {
                if (parent == null) {
                    parent = new BNode(t);
                    root = parent;
                }
                parent.splitChild(childIndex, node);
                if (key == parent.keys[childIndex]) {
                    //Duplicate keys
                    return;
                }

                if (key < parent.keys[childIndex]) {
                    node = parent.children[childIndex];
                } else {
                    node = parent.children[childIndex+1];
                }
            }

            //Binary Search to find where to insert new key
            int left=0; int right=node.n-1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (key == node.keys[mid]) {
                    // Duplicate Node
                    return;
                }
                if (key < node.keys[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            //If not found, traverse down the children
            if (node.isLeaf()) {
                break;
            }

            parent = node;
            childIndex = right + 1;
            node = node.children[childIndex];
        }
        node.insertNodeAsSorted(key);
    }

    public void display(){
        display(root);
        System.out.println();
    }

    private void display(BNode node){

        if(node==null){
            return;
        }

        //Traverse through n keys.
        //Left-Child-Node -> Key ...
        //At the end traverse Right-Child-Node of last key.

        int i;
        if(node.isLeaf()) {
            for (i = 0; i < node.n; i++) {
                System.out.print(node.keys[i] + ", ");
            }
        } else {
            for (i = 0; i < node.n; i++) {
                display(node.children[i]);
                System.out.print(node.keys[i] + ", ");
            }
            display(node.children[i]);
        }
    }


    public static void main(String[] args) {
        BTree bTree = new BTree(3);

        int A[] = {10,20,30,40,50,60,70,80,90,41,42,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108};
        //int A[] = {10,20,30,40,50,60,70,80,90,45, 51,52};

        for(int x: A){
            bTree.insert(x);
        }

        bTree.display();

        System.out.println(Arrays.toString(bTree.findNodeForKey(41).keys));
    }
}
