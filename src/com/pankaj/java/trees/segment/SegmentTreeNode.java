package com.pankaj.java.trees.segment;

public class SegmentTreeNode {
    int val;
    int start;
    int end;
    SegmentTreeNode left;
    SegmentTreeNode right;

    public SegmentTreeNode (int val, int start, int end) {
        this.val = val;
        this.start = start;
        this.end = end;
    }
}
