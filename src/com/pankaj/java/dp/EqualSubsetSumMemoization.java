package com.pankaj.java.dp;

import java.util.Arrays;

public class EqualSubsetSumMemoization {

    /*
     * https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/3jEPRo5PDvx
     */

    public boolean canPartition (int[] num) {
        int sum = Arrays.stream(num).sum();
        if (sum%2 != 0) return false;
        Boolean dp[][] = new Boolean[num.length][sum/2 + 1];
        return canPartitionRecusive(dp, num, sum/2, 0);
    }

    private boolean canPartitionRecusive (Boolean[][] dp, int[] num, int sum, int currentIndex) {
        if (sum == 0) {
            return true;
        }
        if (num.length == 0 || currentIndex >= num.length) {
            return false;
        }
        if (dp[currentIndex][sum] == null) {
            if (num[currentIndex] <= sum) {
                if (canPartitionRecusive(dp, num, sum-num[currentIndex], currentIndex + 1)) {
                    dp[currentIndex][sum] = true;
                    return true;
                }
            }
            dp[currentIndex][sum] = canPartitionRecusive(dp, num, sum, currentIndex + 1);
        }
        return dp[currentIndex][sum];
    }

    public static void main(String[] args) {
        EqualSubsetSumMemoization ps = new EqualSubsetSumMemoization();
        int[] num = { 1, 2, 3, 4 };
        System.out.println(ps.canPartition(num));
        num = new int[] { 1, 1, 3, 4, 7 };
        System.out.println(ps.canPartition(num));
        num = new int[] { 2, 3, 4, 6 };
        System.out.println(ps.canPartition(num));
    }
}
