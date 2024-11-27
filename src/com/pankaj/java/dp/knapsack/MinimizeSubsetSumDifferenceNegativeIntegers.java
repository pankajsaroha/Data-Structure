package com.pankaj.java.dp.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimizeSubsetSumDifferenceNegativeIntegers {

    /*
    * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
    */

    public int minimumDifference(int[] nums) {

        int n1 = 0; int n2 = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] < 0 ) {
                n1++;
            } else {
                n2++;
            }
        }

        int arr1[] = new int[n1];
        int arr2[] = new int[n2];
        int sum1 = 0;
        int sum2 = 0;
        int index1 = 0; int index2 = 0;
        for (int i=0; i<n1; i++) {
            if (nums[i] < 0) {
                arr1[index1++] = Math.abs(nums[i]);
                sum1 += nums[i];
            } else {
                arr2[index2++] = nums[i];
                sum2 += nums[i];
            }
        }

        boolean dp1[][] = subsetSum (arr1, sum1);
        boolean dp2[][] = subsetSum (arr2, sum2);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i=1; i<sum1+1; i++) {
            if (dp1[arr1.length][i]) {
                list1.add(-1 * i);
            }
        }

        for (int i=0; i<sum2+1; i++) {
            if (dp2[arr2.length][i]) {
                list2.add(i);
            }
        }

        Collections.sort(list2);

        for (int s : list1) {

        }

        return 0;
    }

    private boolean[][] subsetSum (int arr[], int sum) {
        boolean[][] dp = new boolean[arr.length+1][sum+1];

        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<sum+1; j++) {
                if (i == 0) {
                    dp[i][j] = (j==0) ? true : false;
                } else {
                    if (arr[i-1] <= j) {
                        dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp;
    }
}
