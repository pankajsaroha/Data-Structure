package com.pankaj.java.trees.binarytree;

import java.util.HashSet;

public class DuplicateSubtree {

    /*
    * https://www.geeksforgeeks.org/check-binary-tree-contains-duplicate-subtrees-size-2/
    * */

    static class Node {
        char val;
        Node left;
        Node right;

        public Node (char val) {
            this.val = val;
        }
    }

    static char MARKER = '$';

    static String duplicate (Node root) {
        HashSet<String> subTrees = new HashSet<>();
        return duplicateUtil(root, subTrees);
    }

    static String duplicateUtil (Node root, HashSet<String> subTrees) {
        String s = "";

        if (root == null) {
            return s + MARKER;
        }

        String left = duplicateUtil(root.left, subTrees);
        if (left.equals(s)) {
            return s;
        }

        String right = duplicateUtil(root.right, subTrees);
        if (right.equals(s)) {
            return s;
        }

        s = s + root.val + left + right;

        if (s.length() > 3 && subTrees.contains(s)) {
            return "";
        }

        subTrees.add(s);

        return s;
    }

    public static void main(String[] args) {
        Node root = new Node('A');
        root.left = new Node('B');
        root.right = new Node('C');
        root.left.left = new Node('D');
        root.left.right = new Node('E');
        root.right.right = new Node('B');
        root.right.right.right = new Node('E');
        root.right.right.left= new Node('D');
        String str = duplicate(root);
        if(str.equals(""))
            System.out.print(" Yes ");
        else
            System.out.print(" No ");
    }
}
