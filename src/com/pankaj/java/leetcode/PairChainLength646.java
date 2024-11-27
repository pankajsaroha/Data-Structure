package com.pankaj.java.leetcode;

import java.util.Arrays;

public class PairChainLength646 {
    //working
    /*public int findLongestChain (int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[pairs.length+1];
        Arrays.fill(dp, -1);
        return longestChain(pairs, 0, Integer.MIN_VALUE, dp);
    }

    public int longestChain (int[][] pairs, int index, int last, int[] dp) {
        if (index >= pairs.length) {
            return 0;
        }
        int include = 0, skip = 0;
        if (last < pairs[index][0]) {
            include = 1 + longestChain(pairs, index + 1, pairs[index][1], dp);
        }
        skip = longestChain(pairs, index + 1, last, dp);
        return dp[index] = Math.max(include, skip);
    }*/

    //not working
    public int findLongestChain (int[][] pairs) {
        return helper(pairs, pairs.length-1);
        /*Arrays.sort(pairs, (a, b) ->  {return a[1] == b[1] ? b[0] - a[0] : a[1] - b[1];});
        int[] dp = new int[pairs.length+1];
        Arrays.fill(dp, -1);
        return longestChain(pairs, pairs.length, Integer.MAX_VALUE, dp);*/
    }

    /*public int longestChain (int[][] pairs, int n, int last, int[] dp) {
        if (n <= 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int include = 0, skip = 0;
        if (pairs[n-1][1] < last) {
            include = 1 + longestChain(pairs, n-1, pairs[n-1][0], dp);
        }
        skip = longestChain(pairs, n-1, last, dp);
        return dp[n] = Math.max(include, skip);
    }*/



    /*working for two samples inputs of leetcode but not sure about all inputs
    we are not sorting so tried to use for loop to check one pair to all other pairs
    but if we sort we just need to compare adjacent pairs so that may be a better approach
    */
    private int helper (int[][] pairs, int n) {
        if (n <= 0) {
            return 0;
        }
        int include = 0, skip = 0;
        for (int i=0; i<n; i++) {
            if (pairs[i][1] < pairs[n][0]) {
                include = 1 + helper(pairs, n-1);
            } else {
                skip = helper(pairs, n-1);
            }
        }
        return Math.max(include, skip);
    }

    public static void main(String[] args) {
        PairChainLength646 p = new PairChainLength646();
        //int[][] pairs = {{1, 2}, {7, 8}, {4, 5}};
        //int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};
        //int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        int[][] pairs = {{9,10},{9,10},{4,5},{-9,-3},{-9,1},{0,3},{6,10},{-5,-4},{-7,-6}};
        System.out.println(p.findLongestChain(pairs));
    }
}
