package com.pankaj.java.dp;

public class KnapshackBasicSolution {

    /*
     * https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60
     */

    public int solveKnapsack (int[] profits, int[] weights, int capacity) {
        return knapshackRecursive(profits, weights, capacity, 0);
    }

    private int knapshackRecursive (int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }

        int profits1 = 0;
        if (weights[currentIndex] <= capacity) {
            profits1 = profits[currentIndex] + knapshackRecursive(profits, weights, capacity-weights[currentIndex], currentIndex + 1);
        }

        int profits2 = knapshackRecursive(profits, weights, capacity, currentIndex+1);

        return Math.max(profits1, profits2);
    }

    public static void main(String[] args) {
        KnapshackBasicSolution ks = new KnapshackBasicSolution();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}