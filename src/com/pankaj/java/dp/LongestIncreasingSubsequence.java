package com.pankaj.java.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubsequence {
    /*
    * https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
    * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
    * https://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming
    */

    // O(n^2)
    static int longestIncreasingSubsequence1 (int arr[]) {
        int maxLength = 1;
        int bestEnd = 0;
        int dp[] = new int[arr.length];
        int prev[] = new int[arr.length];

        dp[0] = 1;
        prev[0] = -1;

        for (int i=1; i<arr.length; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j=i-1; j>=0; j--) {
                if (dp[j] + 1 > dp[i] && arr[j] < arr[i]) {
                    dp[i] = dp[j]+1;
                    prev[i] = j;
                }
            }

            if(dp[i] > maxLength) {
                maxLength = dp[i];
                bestEnd = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[bestEnd]);
        while (prev[bestEnd] != -1) {
            list.add(arr[prev[bestEnd]]);
            bestEnd = prev[bestEnd];
        }
        Collections.sort(list);
        System.out.println(Arrays.toString(list.toArray()));
        return maxLength;
    }

    //Return ceil index
    static int binarySearch(int arr[], int left, int right, int key) {

        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= key) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    static int longestIncreasingSubsequence(int arr[]) {
        //this array contains the end of the new list
        int tailTable[] = new int[arr.length];
        int prev[] = new int[arr.length];

        int len = 1;

        tailTable[0] = arr[0];

        for (int i=1; i<arr.length; i++) {
            prev[i] = -1;
            if(arr[i] < tailTable[0]) {
                tailTable[0] = arr[i];
            } else if (arr[i] > tailTable[len-1]) {
                prev[i] = tailTable[len-1];
                tailTable[len++] = arr[i];
            } else {
                int index = binarySearch(tailTable,-1, len-1, arr[i]);
                prev[i] = tailTable[index-1];
                tailTable[index] = arr[i];
            }
        }

        Arrays.sort(prev);
        System.out.println(Arrays.toString(prev));

        return len;
    }

    static int longestIncreasingSubsequence2(int arr[]) {
        //this array contains the end of the new list
        int tailTable[] = new int[arr.length];
        int len = 1;

        tailTable[0] = arr[0];

        for (int i=1; i<arr.length; i++) {
            if (arr[i] > tailTable[len-1]) {
                tailTable[len++] = arr[i];
            } else {
                int index = Arrays.binarySearch(tailTable, 0, len-1, arr[i]);
                if (index < 0) {
                    index = -1 * index - 1;
                }
                tailTable[index] = arr[i];
            }
        }

        return len;
    }

    public static void main(String[] args) {
        //int arr[] = {69, 54, 19, 51, 16, 54, 64, 89, 72, 40, 31, 43, 1, 11, 82, 65, 75, 67, 25, 98, 31, 77, 55, 88, 85, 76, 35, 101, 44, 74, 29, 94, 72, 39, 20, 24, 23, 66, 16, 95, 5, 17, 54, 89, 93, 10, 7, 88, 68, 10, 11, 22, 25, 50, 18, 59, 79, 87, 7, 49, 26, 96, 27, 19, 67, 35, 50, 10, 6, 48, 38, 28, 66, 94, 60, 27, 76, 4, 43, 66, 14, 8, 78, 72, 21, 56, 34, 90, 89};
        //int arr[] = {3, 10, 2, 11, 9};
        int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(longestIncreasingSubsequence(arr));
    }
}
