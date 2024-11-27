package com.pankaj.java.matrix;

public class KthSmallestInMultiplicationTable {

    static int findKthNumber (int m, int n, int k) {
        int low = 1;
        int high = m*n;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = getCount(m, n, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    static int getCount(int m, int n, int mid) {
        int count = 0;
        for (int i=1; i<=m; i++) {
            count += Math.min(mid/i , n);
        }
        return count;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int k = 5;
        System.out.println(findKthNumber(m, n, k));
    }
}
