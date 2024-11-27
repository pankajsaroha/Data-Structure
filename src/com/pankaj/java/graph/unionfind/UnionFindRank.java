package com.pankaj.java.graph.unionfind;

import java.util.Arrays;

public class UnionFindRank {

    int parent[];
    int rank[];
    Edge[] edges;
    int v;
    int e;

    class Edge {
        int src, dest;
    }

    UnionFindRank (int v, int e) {
        this.v = v;
        this.e = e;
        this.parent = new int[v];
        this.rank = new int[v];
        this.edges = new Edge[e];

        for (int i=0; i<e; i++) {
            this.edges[i] = new Edge();
        }
    }

    int find (int vertex) {
        if (parent[vertex] == -1) {
            return vertex;
        } else {
            return parent[vertex] = find(parent[vertex]);
        }
    }

    void union (int x, int y) {
        int left = find(x);
        int right = find(y);

        if (rank[left] > rank[right]) {
            parent[right] = left;
        } else {
            parent[left] = right;
            if (rank[left] == rank[right]) {
                rank[right]++;
            }
        }

        /*if (rank[left] < rank[right]) {
            parent[left] = right;
        } else if (rank[left] > rank[right]) {
            parent[right] = left;
        } else {
            parent[right] = left;
            rank[left] = rank[left]+1;
        }*/
    }

    boolean isCyclic (UnionFindRank graph) {
        for (int i=0; i<this.e; i++) {
            int x = find(graph.edges[i].src);
            int y = find(graph.edges[i].dest);

            if (x == y) {
                return true;
            }

            graph.union(graph.edges[i].src, graph.edges[i].dest);
        }
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(rank));
        return false;
    }

    public static void main(String[] args) {
        UnionFindRank graph = new UnionFindRank(5, 4);

        for (int i=0; i< graph.v; i++) {
            graph.parent[i] = -1;
        }

        graph.union(0, 2);
        graph.union(4, 2);
        graph.union(3, 1);

        graph.edges[0].src = 0;
        graph.edges[0].dest = 2;

        graph.edges[1].src = 4;
        graph.edges[1].dest = 2;

        graph.edges[2].src = 3;
        graph.edges[2].dest = 1;

        graph.edges[3].src = 4;
        graph.edges[3].dest = 0;

        /*graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;

        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;

        graph.edges[3].src = 3;
        graph.edges[3].dest = 4;*/

        System.out.println(graph.find(0) == graph.find(4) ? "Same set" : "Different Set");
        System.out.println(graph.find(0) == graph.find(1) ? "Same set" : "Different Set");
        System.out.println(graph.find(3) == graph.find(1) ? "Same set" : "Different Set");

        //System.out.println(graph.isCyclic(graph));
    }
}
