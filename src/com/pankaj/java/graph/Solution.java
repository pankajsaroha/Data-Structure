package com.pankaj.java.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int target = image[sr][sc];
        image[sr][sc] = color;

        if (target == color) {
            return image;
        }

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> visited = new LinkedList<>();

        queue.add (new int[]{sr, sc});

        final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] pixel = queue.poll();
            int x = pixel[0], y = pixel[1];
            image[x][y] = color;
            visited.add(pixel);

            for (int[] dir : directions) {
                int row = x + dir[1];
                int col = y + dir[0];

                if (isValid(row, col, image, target)) {
                    queue.add(new int[]{row, col});
                }
            }
        }
        return image;
    }

    private boolean isValid (int row, int col, int[][] image, int target) {
        if (row >= image.length || row < 0 || col >= image[0].length || col < 0 || image[row][col] != target) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution g = new Solution();
        int[][] image = {
            {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        };
        int[][] result = g.floodFill(image, 1, 1, 3);
        Arrays.stream(result).map(Arrays::toString).forEach(System.out::println);
    }
}
