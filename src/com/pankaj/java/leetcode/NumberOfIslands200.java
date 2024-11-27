package com.pankaj.java.leetcode;

public class NumberOfIslands200 {

    // https://leetcode.com/problems/number-of-islands/discuss/56347/Simple-Java-Solution

    // left right combination
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    static int numIslands (int grid[][]) {
        if (grid == null || grid.length == 0) return 0;

        int islands = 0;

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    explore(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    private static void explore (int[][] grid, int i, int j) {
        grid[i][j] = -1;

        for (int d=0; d<dx.length; d++) {
            if (i + dy[d] < grid.length && i + dy[d] >= 0
            && j + dx[d] < grid[0].length && j + dx[d] >= 0
            && grid[i + dy[d]][j + dx[d]] == 1) {
                explore(grid, i + dy[d], j + dx[d]);
            }
        }
    }

    public static void main(String[] args) {
        /*int grid[][] = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };*/
        int grid[][] = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        System.out.println(numIslands(grid));
    }
}
