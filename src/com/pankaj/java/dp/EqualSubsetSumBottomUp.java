package com.pankaj.java.dp;

import java.util.Arrays;

public class EqualSubsetSumBottomUp {

    /*
    * https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/3jEPRo5PDvx
    */

    public boolean canPartition (int[] num) {
        int sum = Arrays.stream(num).sum();
        if (sum%2 != 0) {
            return false;
        }
        sum = sum/2;
        boolean[][] dp = new boolean[num.length][sum+1];

        // populate the sum=0 column, as we can always have '0' sum without including any element
        for (int i=0; i<num.length; i++) {
            dp[i][0] = true;
        }

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (int s=1; s<sum; s++) {
            dp[0][s] = num[0] == s ? true : false;
        }

        //process all other subset for all sums
        for (int i=1; i<num.length; i++) {
            for (int s=1; s<=sum; s++) {
                if (dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) {
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }
        return dp[num.length-1][sum];
    }

    public static void main(String[] args) {
        EqualSubsetSumBottomUp ps = new EqualSubsetSumBottomUp();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
