package com.pankaj.java.dp;

public class GoldMine {
    /*
    * https://www.geeksforgeeks.org/gold-mine-problem/
    */

    static int getMaxGold (int gold[][]) {
        int m = gold.length;
        int n = gold[0].length;

        int goldTable[][] = new int[m+1][n+1];

        for (int col = n-1; col >= 0; col--) {
            for (int row = 0; row < m; row++) {
                int right = (col == n-1) ? 0 : goldTable[row][col+1];
                int righUp = (row == 0 || col == n-1) ? 0 : goldTable[row-1][col+1];
                int rightDown = (row == m-1 || col == n-1) ? 0 : goldTable[row+1][col+1];

                goldTable[row][col] = gold[row][col] + Math.max(right, Math.max(righUp, rightDown));
            }
        }

        int res = goldTable[0][0];

        for (int i=1; i<m; i++) {
            res = Math.max(res, goldTable[i][0]);
        }
        return res;
    }

    public static void main(String[] args) {
        int gold[][]= { {1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2} };

        int m = 4, n = 4;

        System.out.print(getMaxGold(gold));
    }
}
