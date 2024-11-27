package com.pankaj.java.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimizeSubsetSumDifference {

    static boolean dp[][];

    static int minSubsetSumDiff (int arr[], int sum) {
        for (int i=0; i<arr.length+1; i++) {
            for (int j=0; j<sum+1; j++) {
                if (i == 0) {
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
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<=(sum/2); i++) {
            if (dp[arr.length][i]) {
                list.add(i);
            }
        }

        return sum - 2 * list.get(list.size()-1);
    }

    public static void main(String[] args) {
        int arr[] = {-3, 3};
        int sum = Arrays.stream(arr).sum();
        dp = new boolean[arr.length+1][sum+1];
        System.out.println(minSubsetSumDiff(arr, sum));
    }
}
