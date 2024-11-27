package com.pankaj.java;

import java.util.Arrays;

public class PredictWinner {

    static int dp[][];
    public static void main(String args[]) {
        dp = new int[4][4];
        for (int i=0; i<4; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(predictWinner(new int[] {1, 5, 8, 4}, 0, 3, 1) >= 0);
    }

    static int predictWinner (int[] arr, int s, int e, int turn) {
        if (s == e) {
            return arr[s];
        }
        if (dp[s][e] != -1) return dp[s][e];
        int a = arr[s] + predictWinner(arr, s+1, e, -turn);
        int b = arr[e] - predictWinner(arr, s, e-1, -turn);

        return dp[s][e] = Math.max(a, b);
    }

}