package com.pankaj.java.graph.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    int[] parent;
    int[] size;

    List<List<Integer>> graph;

    public Solution (int v) {
        parent = new int[v];
        size = new int[v];

        graph = new ArrayList<>();
        for (int i=0; i<v; i++) {
            graph.add(new ArrayList<>());
            parent[i] = i;
        }
    }

    public int find (int u) {
        if (parent[u] == u) {
            return u;
        }
        return parent[u] = find (parent[u]);
    }

    public void union (int u, int v) {
        int x = find(u);
        int y = find(v);

        if (x == y) {
            System.out.println("Cyclic");
            return;
        }
        /*if (size[x] > size[y]) {
            parent[y] = x;
        } else if (size[x] < size[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            size[x]++;
        }*/
        if (size[x] > size[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
            if (size[y] == size[x]) {
                size[y]++;
            }
        }
    }

    public boolean isCyclic (List<List<Integer>> graph) {
        for (int u = 0; u < graph.size(); u++) {
            for (int v : graph.get(u)) {
                union(u, v);
            }
        }
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(size));
        return false;
    }

    public static void main(String[] args) {
        /*int[][] graph = {
                {0, 1},
                {0, 2},
                //{1, 2},
                {0, 3},
                {3, 4}
        };*/
        Solution s = new Solution(5);
        s.graph.get(0).add(1);
        s.graph.get(0).add(2);
        s.graph.get(0).add(3);
        s.graph.get(3).add(4);

        new Solution(5).isCyclic(s.graph);
    }
}
