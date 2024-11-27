package com.pankaj.java.trees.segment;

public class ReversePair {

    SegmentTreeNode root;

    public static void main(String[] args) {
        ReversePair reversePair = new ReversePair();
        int arr[] = {8, 4, 2, 1};
        reversePair.root = new SegmentTreeNode(0, 0, arr.length - 1);
    }
}
