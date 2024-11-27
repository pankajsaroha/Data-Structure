package com.pankaj.java.trees.btree;

public class BTreeProgramiz {

    private int t;
    private Node root;

    class Node {
        int n;      //current number of nodes
        int key[] = new int[2*t - 1];       // number of keys in the node
        Node child[] = new Node[2*t];       // number of children
        boolean leaf = true;

        public int find (int k) {
            for (int i=0; i<this.n; i++) {
                if (key[i] == k) {
                    return i;
                }
            }
            return -1;
        }
    }

    public BTreeProgramiz(int t) {
        this.t = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    public void insert (int data) {
        Node oldRoot = root;
        if (root.n == 2*t-1) {
            Node node = new Node();
            root = node;
            node.leaf = false;
            node.child[0] = oldRoot;
            split(node, 0, oldRoot);
            insertValue(node, data);
        } else {
            insertValue(root, data);
        }
    }

    private void insertValue (Node x, int k) {
        if (x.leaf) {
            int i;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
                x.key[i+1] = x.key[i];
            }
            x.key[i+1] = k;
            x.n++;
        } else {
            int i=0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {

            }
            i++;
            Node temp = x.child[i];

            if (temp.n == 2 * t - 1) {
                split(x, i, temp);
                if (k > x.key[i]) {
                    i++;
                }
            }
            insertValue(x.child[i], k);
        }
    }

    private void split (Node x, int pos, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = t - 1;
        for (int i=0; i<t-1; i++) {
            z.key[i] = y.key[i+t];
        }
        if (!y.leaf) {
            for (int i=0; i<t; i++) {
                z.child[i] = y.child[i+t];
            }
        }
        y.n = t - 1;
        for (int i=x.n; i>=pos+1; i--) {
            x.child[i+1] = x.child[i];
        }
        x.child[pos+1] = z;

        for (int i=x.n; i>=pos; i--) {
            x.key[i+1] = x.key[i];
        }
        x.key[pos] = y.key[t-1];
        x.n = x.n + 1;
    }

    public void show () {
        show(root);
    }

    private void show (Node node) {
        assert (node == null);
        for (int i = 0; i < node.n; i++) {
            System.out.print(node.key[i] + " ");
        }
        if (!node.leaf) {
            for (int i = 0; i < node.n + 1; i++) {
                show(node.child[i]);
            }
        }
    }

    public static void main(String[] args) {
        BTreeProgramiz bTree = new BTreeProgramiz(3);
        bTree.insert(8);
        bTree.insert(9);
        bTree.insert(10);
        bTree.insert(11);
        bTree.insert(15);
        bTree.insert(20);
        bTree.insert(17);

        bTree.show();
    }
}
