package com.pankaj.java.dp;

public class KnapshackBottomUpDP {

    //https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        //basic check
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length) {
            return 0;
        }
        int n = profits.length;
        int dp[][] = new int[n][capacity+1];

        //populate the capacity=0 columns with 0, we have 0 profit for 0 capacity
        for (int i=0; i<n; i++) {
            dp[i][0] = 0;
        }

        //if we have only one weight, we will take it if it is not more than the capacity
        for (int c=0; c<=capacity; c++) {
            if (weights[0] <= c) {
                dp[0][c] = profits[0];
            }
        }

        //process all the sub arrays for all the capacities
        for (int i=1; i<n; i++) {
            for (int c=1; c<=capacity; c++) {
                int profits1 = 0, profits2 = 0;

                //include the item if it is not more than the capacity
                if(weights[i] <= c) {
                    profits1 = profits[i] + dp[i-1][c-weights[i]];
                }
                //exclude the item
                profits2 = dp[i-1][c];

                //take maximum
                dp[i][c] = Math.max(profits1, profits2);
            }
        }
        printSelectedElements(dp, profits, weights, capacity);
        return dp[n-1][capacity];
    }

    private void printSelectedElements (int[][] dp, int[] profits, int[] weights, int capacity) {
        System.out.print("Selected weights:");
        int totalProfit = dp[weights.length-1][capacity];
        for (int i=weights.length-1; i>0; i--) {
            if (totalProfit != dp[i-1][capacity]) {
                System.out.print(" " + weights[i]);
                totalProfit -= profits[i];
                capacity -= weights[i];
            }
        }
        if (totalProfit != 0) {
            System.out.print(" " + weights[0]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        KnapshackBottomUpDP ks = new KnapshackBottomUpDP();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
