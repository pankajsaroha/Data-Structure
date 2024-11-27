package com.pankaj.java.dp.lcs;

import java.util.Arrays;

public class WildcardPatternMatching {

    static int t[][];

    //Recursive not working
    public int patternMatchingRecursive (String str, String pattern, int m, int n) {

        if (m == 0 && n == 0) {
            return t[m][n] = 1;
        }

        if (n == 0) {
            return 0;
        }

        if (m == 0) {
            if (pattern.charAt(n-1) == '*') {
                t[m][n] = t[m][n-1];
            }
            return t[m][n];
        }

        if (t[m][n] != -1) {
            return t[m][n];
        }

        if (str.charAt(m-1) == pattern.charAt(n-1) || pattern.charAt(n-1) == '?') {
            return t[m][n] = patternMatchingRecursive(str, pattern, m-1, n-1);
        } else if (pattern.charAt(n-1) == '*') {
            return t[m][n] = Math.max(patternMatchingRecursive(str, pattern, m-1, n), patternMatchingRecursive(str, pattern, m, n-1));
        }

        return t[m][n];
    }

    public boolean patternMatching (String str, String pattern) {
        int m = str.length();
        int n = pattern.length();
        boolean dp[][] = new boolean[m+1][n+1];

        dp[0][0] = true;

        for (int i=1; i<n+1; i++) {
            if (pattern.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-1];
            }
        }

        for (int i=1; i<m+1; i++) {
            for (int j=1; j<n+1; j++) {
                if (str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    if (pattern.charAt(j-1) == '*') {
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        WildcardPatternMatching wp = new WildcardPatternMatching();
        String str = "aaaa";
        String pattern = "*";
        t = new int[str.length()+1][pattern.length()+1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        //System.out.println(wp.patternMatching(str, pattern));
        System.out.println(wp.patternMatchingRecursive(str, pattern, str.length(), pattern.length()));
    }
}
