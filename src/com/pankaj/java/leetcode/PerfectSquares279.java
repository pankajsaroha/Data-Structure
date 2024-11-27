package com.pankaj.java.leetcode;

public class PerfectSquares279 {

    public int numSquares(int n) {
        return helper(n, new int[0]);
    }

    private int helper (int n, int[] dp) {
        if (n <= 0) {
            return 0;
        }
        int res = n;
        for (int i=1; i*i <= n; i++) {
            res = Math.min(res, 1 + helper(n - i * i, dp));
        }
        return res;
    }

    public static void main(String[] args) {
        PerfectSquares279 p = new PerfectSquares279();
        System.out.println(p.numSquares(13));
    }
}
