package com.pankaj.java.trees.segment;

import java.util.Arrays;

public class SumInRange {

    public void build (int[] tree, int[] arr, int index, int left, int right) {
        if (left == right) {
            tree[index] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        build(tree, arr, 2 * index + 1, left, mid);
        build(tree, arr, 2 * index + 2, mid + 1, right);

        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    static void update (int tree[], int arr[], int index, int start, int end, int val, int replaceIndex) {
        if (start == end) {
            arr[replaceIndex] = val;
            tree[index] = val;
        } else {
            int mid = start + (end - start) / 2;
            if (start <= replaceIndex && replaceIndex <= mid) {
                update(tree, arr, 2 * index + 1, start, mid, val, replaceIndex);
            } else {
                update(tree, arr, 2 * index + 2, mid + 1, end, val, replaceIndex);
            }
            tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
        }
    }

    static int query (int[] tree, int index, int start, int end, int l, int r) {
        if (r < start || l > end) {
            return 0;
        }

        if (l <= start && end <= r) {
            return tree[index];
        }

        int mid = start + (end - start) / 2;
        int left = query(tree, 2 * index + 1, start, mid, l, r);
        int right = query(tree, 2 * index + 2, mid + 1, end, l, r);
        return left + right;
    }

    public static void main(String[] args) {
        SumInRange segmentTree = new SumInRange();
        int arr[] = {1, 3, 5, 7, 9, 11};
        int tree[] = new int[4*arr.length];
        segmentTree.build(tree, arr, 0, 0, arr.length-1);
        System.out.println(Arrays.toString(tree));
        System.out.println(query(tree, 0, 0, arr.length-1, 4, 5));
        update(tree, arr, 0, 0, arr.length -1, 12, 5);
        System.out.println(Arrays.toString(tree));
    }
}
