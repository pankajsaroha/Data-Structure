package com.pankaj.java.dp;

public class LongestCommonSubsequence {

    /*
    * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
    */

    public int longestCommonSubsequence(String str1, String str2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        } else if (str1.charAt(m-1) == str2.charAt(n-1)) {
            return 1 + longestCommonSubsequence(str1, str2, m-1, n-1);
        } else {
            return Math.max(longestCommonSubsequence(str1, str2, m-1, n),
                    longestCommonSubsequence(str1, str2, m, n-1));
        }
    }

    public int longestCommonSubsequenceDP(String str1, String str2, int m, int n) {
        int dp[][] = new int[m+1][n+1];
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        printLCS(str1, str2, dp);
        return dp[m][n];
    }

    void printLCS (String str1, String st2, int dp[][]) {
        StringBuilder sb = new StringBuilder();
        int m = dp.length-1;
        int n = dp[0].length-1;
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if(dp[i][j] == 1 + dp[i-1][j-1]) {
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(sb.reverse().toString());
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "bbbaabaa";
        String str2 = "aababbabb";

        System.out.println(lcs.longestCommonSubsequenceDP(str1, str2, str1.length(), str2.length()));
    }
}
