package com.pankaj.java.trees.btree;

public class BTreePractice {

    private int t;
    BTreeNode root;

    public BTreePractice (int t) {
        this.t = t;
    }

    public void insert (int key) {
        if (root == null) {
            root = new BTreeNode(t);
            root.insertNodeAsSorted(key);
            return;
        }

        BTreeNode parent = null;
        BTreeNode node = root;
        int childIndex = 0;

        while (true) {
            if(node.isFull()) {
                if (parent == null) {
                    parent = new BTreeNode(t);
                    root = parent;
                }
                parent.splitNode(childIndex, node);

                if (key < parent.keys[childIndex]) {
                    node = parent.children[childIndex];
                } else {
                    node = parent.children[childIndex+1];
                }
            }

            int left=0; int right = node.n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (key < node.keys[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (node.isLeaf()) {
                break;
            }

            parent = node;
            childIndex = right + 1;
            node = node.children[childIndex];
        }
        node.insertNodeAsSorted(key);
    }

    public void display () {
        display(root);
        System.out.println();
    }

    private void display (BTreeNode node) {
        if (node == null) {
            return;
        }
        int i;
        if (node.isLeaf()) {
            for (i=0; i<node.n; i++) {
                System.out.print(node.keys[i] + ", ");
            }
        } else {
            for (i=0; i<node.n; i++) {
                display(node.children[i]);
                System.out.print(node.keys[i] + ", ");
            }
            display(node.children[i]);
        }
    }

    public static void main(String[] args) {
        BTreePractice bTree = new BTreePractice(3);

        int A[] = {10,20,30,40,50,60,70,80,90,41,42,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108};
        //int A[] = {10,20,30,40,50,60,70,80,90,45, 51,52};

        for(int x: A){
            bTree.insert(x);
        }

        bTree.display();
    }
}

class BTreeNode {

    public int t;
    public int n;
    public int keys[];
    public BTreeNode children[];

    public BTreeNode (int t) {
        this.t = t;
        this.keys = new int[2*t-1];
    }

    public boolean isFull () {
        return this.n == 2 * t - 1;
    }

    public boolean isLeaf () {
        return this.children == null;
    }

    public void insertNodeAsSorted (int key) {
        int i;
        for (i=this.n-1; i>=0 && key < this.keys[i]; i--) {
            this.keys[i+1] = this.keys[i];
        }
        this.keys[i+1] = key;
        this.n++;
    }

    public void splitNode (int childIndex, BTreeNode childNode) {
        BTreeNode rightChild = new BTreeNode(t);
        int middleIndex = childNode.n/2;

        int j=0; int i;
        for (i=middleIndex+1; i<childNode.n; i++) {
            rightChild.keys[j++] = childNode.keys[i];
            childNode.keys[i] = -1;
        }

        if (!childNode.isLeaf()) {
            rightChild.children = new BTreeNode[2*t];

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

        //Move up the middleKey
        if (this.children == null) {
            this.children = new BTreeNode[2*t];
        }

        this.children[n+1] = this.children[n];
        for (i=this.n-1; i>childIndex; i--) {
            this.keys[n] = this.keys[n-1];
            this.children[n] = this.children[n-1];
        }

        this.keys[childIndex] = childNode.keys[middleIndex];
        childNode.keys[middleIndex] = -1;
        this.n++;
        this.children[childIndex] = childNode;
        this.children[childIndex+1] = rightChild;
    }
}
