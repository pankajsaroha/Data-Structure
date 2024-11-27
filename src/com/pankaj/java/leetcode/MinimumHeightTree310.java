package com.pankaj.java.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTree310 {

    /*
    * Another Solution - https://leetcode.com/problems/minimum-height-trees/discuss/76142/35ms-Concise-Java-Solution
    * */

    public List<Integer> findMHT (int n, int[][] edges) {
        if (n < 2) {
            List<Integer> centroids = new ArrayList<>();
            for (int i=0; i<n; i++) {
                centroids.add(i);
            }
            return centroids;
        }

        ArrayList<Set<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++) {
            adj.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            adj.get(start).add(end);
            adj.get(end).add(start);
        }

        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (adj.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        int remainingNodes = n;

        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            for (Integer leaf : leaves) {
                Integer neighbor = adj.get(leaf).iterator().next();
                adj.get(neighbor).remove(leaf);
                if (adj.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public static void main(String[] args) {
        MinimumHeightTree310 mht = new MinimumHeightTree310();
        int n = 6;
        //int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(mht.findMHT(n, edges).toString());
    }
}
