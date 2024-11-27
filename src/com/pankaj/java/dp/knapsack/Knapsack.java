package com.pankaj.java.dp.knapsack;

import java.util.Arrays;

public class Knapsack {

    public int knapsack (int weights[], int profits[], int n, int w) {
        if (n == 0 || w == 0) {
            return 0;
        }
        if (weights[n-1] <= w) {
            return Math.max(profits[n-1] + knapsack(weights, profits, n-1, w - weights[n-1]),
                    knapsack(weights, profits, n-1, w));
        } else {
            return knapsack(weights, profits, n-1, w);
        }
    }

    static int dp[][];

    public int knapsackMemoization (int weights[], int profits[], int n, int w) {
        if (n == 0 || w == 0) {
            return 0;
        }
        if (dp[n][w] != -1) {
            return dp[n][w];
        }
        if (weights[n-1] <= w) {
            dp[n][w] = Math.max(profits[n-1] + knapsack(weights, profits, n-1, w - weights[n-1]),
                    knapsack(weights, profits, n-1, w));
        } else {
            dp[n][w] = knapsack(weights, profits, n, w);
        }
        return dp[n][w];
    }

    public int knapsackTopDown (int weights[], int profits[], int n, int w) {
        for (int i=0; i<n+1; i++) {
            for (int j=0; j<w+1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(profits[i-1] + dp[i-1][j-weights[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        Knapsack k = new Knapsack();
        //int[] profits = {1, 6, 10, 16};
        //int[] weights = {1, 2, 3, 5};
        //int w = 7;
        int[] profits = {1, 2, 3};
        int[] weights = {4, 5, 1};
        int w = 4;
        dp = new int[profits.length + 1][w + 1];
        for (int arr[] : dp) {
            Arrays.fill(arr, -1);
        }
        System.out.println(k.knapsackTopDown(weights, profits, weights.length, w));
    }
}
