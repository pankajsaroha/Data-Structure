package com.pankaj.java.dp;

public class PermutationCoefficient {

    /*
    * https://www.geeksforgeeks.org/permutation-coefficient/
    * P(n, k) = P(n-1, k) + k * P(n-1, k-1)
    * nPk = n! / (n-k)!
    */

    public int bottomUp (int n, int k) {
        int dp[][] = new int[n+2][k+2];

        for (int i=0; i<=n; i++) {
            for (int j=0; j<=Math.min(i, k); j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + (j * dp[i-1][j-1]);
                }
                // This step is important as P(i,j)=0 for j>i
                dp[i][j+1] = 0;
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        PermutationCoefficient pc = new PermutationCoefficient();
        int n = 5, k = 2;
        System.out.println("Bottom Up - Permutation Coefficient of P( " + n + ","+ k +") is " + pc.bottomUp(n, k));
    }
}
