package com.pankaj.java.dp;

public class BinomialCoefficient {

    /*
    * https://www.geeksforgeeks.org/binomial-coefficient-dp-9/
    * nCr = n! / (n-r)! * r!
    * C(n, r) = C(n-1, r-1) + C(n-1, r)
    */

    //Optimal Substructure
    public int basicSolution(int n, int k) {
        if (k > n) {
            return 0;
        }
        if(k == 0 || k == n) {
            return 1;
        }
        return basicSolution(n-1, k-1) + basicSolution(n-1, k);
    }

    //Overlapping Subproblems

    public int bottomUp (int n, int k) {
        if(k > n) return 0;

        int dp[][] = new int[n+1][k+1];

        for (int i=0; i<=n; i++) {
            for (int j=0; j<=min(i, k); j++) {
                if (j ==0 || j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[n][k];
    }

    private int min (int i, int k) {
        return i > k ? k : i;
    }

    public int bottomUpSpaceOptimized (int n, int k) {
        int dp[] = new int[k+1];

        //nC0 = 1
        dp[0] = 1;

        for (int i=1; i<=n; i++) {
            for (int j=min(i, k); j>0; j--) {
                dp[j] = dp[j] + dp[j-1];
            }
        }

        return dp[k];
    }

    public int memoization (int n, int k) {
        int dp[][] = new int[n+1][k+1];

        for (int i=0; i<=n; i++) {
            for (int j=0; j<=k; j++) {
                dp[i][j] = -1;
            }
        }
        return memoizationUtil(dp, n, k);
    }

    private int memoizationUtil (int[][] dp, int n, int k) {
        if(dp[n][k] != -1) {
            return dp[n][k];
        }
        if(k == 0) {
            dp[n][k] = 1;
            return dp[n][k];
        }
        if(k == n) {
            dp[n][k] = 1;
            return dp[n][k];
        }
        dp[n][k] = memoizationUtil(dp, n-1, k-1) + memoizationUtil(dp, n-1, k);
        return dp[n][k];
    }

    public static void main(String[] args) {
        BinomialCoefficient bc = new BinomialCoefficient();
        int n = 4, k = 2;
        System.out.println("Basic Solution - Binomial Coefficient of (" + n + ", " + k +") = " + bc.basicSolution(4, 2));
        System.out.println("Bottom Up - Binomial Coefficient of (" + n + ", " + k +") = " + bc.bottomUp(4, 2));
        System.out.println("Bottom Up Space Optimized - Binomial Coefficient of (" + n + ", " + k +") = " + bc.bottomUpSpaceOptimized(4, 2));
        System.out.println("Memoization - Binomial Coefficient of (" + n + ", " + k +") = " + bc.memoization(4, 2));
    }
}