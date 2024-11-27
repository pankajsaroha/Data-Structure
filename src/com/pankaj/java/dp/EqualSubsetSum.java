package com.pankaj.java.dp;

import java.util.Arrays;

public class EqualSubsetSum {

    /*
     * https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/3jEPRo5PDvx
     */

    public boolean canPartition (int[] num) {
        int sum = Arrays.stream(num).sum();
        if (sum%2 != 0) return false;
        return canPartitionRecursive(num, sum/2, 0);
    }

    private boolean canPartitionRecursive (int[] num, int sum, int currentIndex) {
        if (sum == 0) {
            return true;
        }
        if (num.length == 0 || currentIndex >= num.length) {
            return false;
        }

        if (num[currentIndex] <= sum) {
            if (canPartitionRecursive(num, sum-num[currentIndex], currentIndex + 1)) {
                return true;
            }
        }
        return canPartitionRecursive(num, sum, currentIndex + 1);
    }

    public static void main(String[] args) {
        EqualSubsetSum ps = new EqualSubsetSum();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
