package com.pankaj.java.dp.knapsack;

import java.util.Arrays;

public class EqualSumPartition {

    public static boolean dp[][];

    static boolean doesEqualSumPartitionSubsetExist (int arr[], int sum) {
        for (int i=0; i<arr.length+1; i++) {
            for (int j=0; j<sum+1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = j == 0 ? true : false;
                } else {
                    if (arr[i-1] <= j) {
                        dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[arr.length][sum];
    }

    public static void main(String[] args) {
        int arr[] = {1, 5, 11, 5};
        int sum = Arrays.stream(arr).sum();
        if (sum %2 == 1) System.out.println("Not present.");
        sum = (sum)/2;
        dp = new boolean[arr.length+1][sum+1];
        System.out.println(doesEqualSumPartitionSubsetExist(arr, sum) ? "Present." : "Not present.");
    }
}
