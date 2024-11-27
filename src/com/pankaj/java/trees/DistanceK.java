package com.pankaj.java.trees;

import com.pankaj.java.Node;
import com.pankaj.java.trees.binarytree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class DistanceK {

    Node root;

    ArrayList<Node> list;

    /*private void printKDistanceNodeDown (Node node, int k) {
        if (node == null || k < 0) {
            return;
        }
        if (k == 0) {
            list.add(node.data);
            return;
        }
        printKDistanceNodeDown(node.left, k-1);
        printKDistanceNodeDown(node.right, k-1);
    }

    public int printKDistanceNode (Node node, int target, int k) {
        if (node == null) {
            return -1;
        }
        if (node.data == target) {
            printKDistanceNodeDown(node, k);
            return 0;
        }
        int distanceLeft = printKDistanceNode(node.left, target, k);

        if (distanceLeft != -1) {
            if (distanceLeft + 1 == k) {
                list.add(node.data);
            } else {
                printKDistanceNodeDown(node.right, k - distanceLeft - 2);
            }
            return 1 + distanceLeft;
        }

        int distanceRight = printKDistanceNode(node.right, target, k);

        if (distanceRight != -1) {
            if (distanceRight + 1 == k) {
                list.add(node.data);
            } else {
                printKDistanceNodeDown(node.left, k - distanceRight - 2);
            }
            return 1 + distanceRight;
        }

        return -1;
    }*/


    public List<Integer> distanceK (Node node, int target, int k) {
        nodeToRootPath(node, target);
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<list.size(); i++) {
            if (i == 0) {
                kLevelDown(list.get(i), k-i, 0, null, ans);
            } else {
                kLevelDown(list.get(i), k-i, 0, list.get(i-1), ans);
            }
        }
        return ans;
    }

    public boolean nodeToRootPath (Node node, int target) {
        if (node == null) {
            return false;
        }
        if (node.data == target) {
            list.add(node);
            return true;
        }
        boolean left = nodeToRootPath(node.left, target);
        if (left) {
            list.add(node);
            return true;
        }
        boolean right = nodeToRootPath(node.right, target);
        if (right) {
            list.add(node);
            return true;
        }
        return false;
    }

    private void kLevelDown (Node node, int k, int dist, Node blocker, List<Integer> ans) {
        if (node == null) {
            return;
        }
        if (node == blocker) {
            return;
        }
        if (dist == k) {
            ans.add(node.data);
            return;
        }
        kLevelDown(node.left, k, dist + 1, blocker, ans);
        kLevelDown(node.right, k, dist + 1, blocker, ans);
    }

    public void inorder (Node root ) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        DistanceK d = new DistanceK();
        //int arr[] = {3, 5, 1, 6, 2, 0, 8, 7, 4};
        /*BinaryTree binaryTree = new BinaryTree();
        for (int data : arr) {
            binaryTree.insert(data);
        }
        d.root = BinaryTree.root;
        d.list = new ArrayList<>();
        d.inorder(d.root);*/
        d.root = new Node(20);
        d.root.left = new Node(8);
        d.root.left.left = new Node(4);
        d.root.left.right = new Node(12);
        d.root.left.right.left = new Node(10);
        d.root.left.right.right = new Node(14);
        d.root.right = new Node(22);

        d.list = new ArrayList<>();

        System.out.println();
        System.out.println(d.distanceK(d.root, 8, 2).toString());
    }
}
