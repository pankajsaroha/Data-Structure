package com.pankaj.java;

import java.util.*;

public class Test2 {

    public static void main(String[] args) {
        Test2 t = new Test2();
        String s = "babad";
        System.out.println(t.longestPalindrome(s));
    }

    int[][] dp;
    public String longestPalindrome(String s) {
        int n = s.length();
        dp = new int[n+1][n+1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        String reversed = reverse(s);
        maxPalindrome (s, reversed, n, n);
        return prepareOutput(s, reversed, n);
    }

    /*private int maxPalindrome (String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            return dp[m][n] = 1 + maxPalindrome (s1, s2, m-1, n-1);
        } else {
            return dp[m][n] = Math.max (maxPalindrome (s1, s2, m, n-1), maxPalindrome(s1, s2, m-1, n));
        }
    }*/

    private void maxPalindrome (String s1, String s2, int m, int n) {
        for (int i=0; i<n+1; i++) {
            for (int j=0; j<n+1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (s1.charAt(i-1) == s2.charAt(j-1)) {
                        dp[i][j] = 1 + dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                    }
                }
            }
        }
    }

    private String reverse (String s) {
        StringBuilder sb = new StringBuilder();
        for (int i=s.length()-1; i>=0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private String prepareOutput (String s1, String s2, int n) {
        int i=n, j=n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j-1] + 1) {
                sb.append(s1.charAt(i-1));
                i--; j--;
            } else if (dp[i][j] == dp[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.toString();
    }
}
