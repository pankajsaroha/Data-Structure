package com.pankaj.java.leetcode;

public class Test {

    static int maxVacation (int arr[], int n, int k) {
        int val[] = new int[n];
        for (int index : arr) {
            val[index-1] = 1;
        }

        int p1 = 0, p2 = 0, max = 0, cur = 0;

        while (p1 < n && p2 < n) {
            if (cur <= k) {
                max = Math.max(max, p2 - p1 + 1);
                ++p2;
                if (p2 < n) {
                    cur += val[p2];
                }
            } else {
                cur -= val[p1];
                ++p1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxVacation(new int[]{1, 3, 6}, 7, 2));
    }
}
