package com.pankaj.java.dp.knapsack;

import java.util.Arrays;

public class SubsetGivenSum {

    static int dp[][];
    static boolean t[][];

    //Initialize first row as 0 and column as 1. First cell will be 1 as 0 sum can be achieved by 0 elements.

    static int countSubsetGivenSum (int arr[], int sum) {
        for (int i=0; i<arr.length+1; i++) {
            for (int j=0; j<sum+1; j++) {
                if (i == 0) {
                    dp[i][j] = j == 0 ? 1 : 0;
                } else {
                    if (arr[i-1] <= j) {
                        dp[i][j] = dp[i-1][j - arr[i-1]] + dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[arr.length][sum];
    }

    static boolean isSubSetGivenSumExist (int arr[], int sum) {
        for (int i=0; i<arr.length+1; i++) {
            for (int j=0; j<sum+1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = j == 0 ? true : false;
                } else {
                    if (arr[i-1] <= j) {
                        t[i][j] = t[i-1][j-arr[i-1]] || t[i-1][j];
                    } else {
                        t[i][j] = t[i-1][j];
                    }
                }
            }
        }
        return t[arr.length][sum];
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 6, 7};
        int sum = 12;

        dp = new int[arr.length+1][sum+1];
        t = new boolean[arr.length+1][sum+1];
        System.out.println(countSubsetGivenSum(arr, sum));

        System.out.println(isSubSetGivenSumExist(arr, sum));

        for (int[] a : dp) {
            System.out.println(Arrays.toString(a));
        }
    }
}
