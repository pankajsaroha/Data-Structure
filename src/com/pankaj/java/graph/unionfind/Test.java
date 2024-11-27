package com.pankaj.java.graph.unionfind;

import java.util.Arrays;

public class Test {

    int parent[];
    int rank[];

    public Test(int vertices) {
        this.parent = new int[vertices];
        this.rank = new int[vertices];

        for (int i=0; i<vertices; i++) {
            parent[i] = i;
        }
    }

    public int find (int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union (int x, int y) {
        int left = find(x);
        int right = find(y);

        if (left == right) {
            return false;
        } else if (rank[left] < rank[right]) {
            parent[left] = right;
        } else if (rank[left] > rank[right]) {
            parent[right] = left;
        } else {
            parent[right] = left;
            rank[left]++;
        }
        return true;
    }

    public static void main(String[] args) {
        Test t = new Test(6);
        int edges[][] = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 4},
                {1, 5}
        };

        for (int edge[] : edges) {
            if (!t.union(edge[0], edge[1])) {
                System.out.println(Arrays.toString(edge));
                break;
            }
        }
    }
}
