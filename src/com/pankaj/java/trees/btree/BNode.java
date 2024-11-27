package com.pankaj.java.trees.btree;

public class BNode {

    //https://github.com/anandbikas/coding/commit/76b99d8e8317e4271f6e8f1f1bdc9c3df1a3ec5f

    //Minimum Degree
    public int t;

    //Number of keys in a node
    public int keys[];

    //Number of current keys in node
    public int n;
    public BNode children[];

    public BNode (int t) {
        this.t = t;
        this.keys = new int[2*t-1];
    }

    public boolean isFull () {
        return n == 2*t-1;
    }

    public boolean isLeaf () {
        return children == null;
    }

    public void insertNodeAsSorted (int key) {
        int i;
        for (i=n-1; i>=0 && key < keys[i]; i--) {
            keys[i+1] = keys[i];
        }
        keys[i+1] = key;
        n++;
    }

    public void splitChild (int childIndex, BNode childNode) {
        BNode rightChild = new BNode(t);
        int middleIndex = childNode.n / 2;

        int j=0; int i;
        for (i=middleIndex+1; i<childNode.n; i++) {
            rightChild.keys[j++] = childNode.keys[i];
            childNode.keys[i] = -1;
        }

        if (!childNode.isLeaf()) {
            rightChild.children = new BNode[2*t];

            j=0;
            for (i=middleIndex+1; i<childNode.n; i++) {
                rightChild.children[j++] = childNode.children[i];
                childNode.children[i] = null;
            }

            rightChild.children[j] = childNode.children[i];
            childNode.children[i] = null;
        }


        childNode.n = middleIndex;
        rightChild.n = j;

        //Move up the middleKey and update left and right child
        if (this.children == null) {
            this.children = new BNode[2*t];
        }
        children[n+1] = children[n];

        for (i=n; i>childIndex; i--) {
            keys[i] = keys[i-1];
            children[i] = children[i-1];
        }

        keys[childIndex] = childNode.keys[middleIndex];
        children[childIndex] = childNode;
        children[childIndex+1] = rightChild;
        this.n++;
        childNode.keys[middleIndex] = -1;
    }
}
