package com.pankaj.java.graph.unionfind;

public class UnionFind {

    //parent[i] stores the parent of ith element in array

    class Edge {
        int src, dest;
    }

    int v;
    int e;
    Edge[] edges;

    UnionFind (int v, int e) {
        this.v = v;
        this.e = e;
        this.edges = new Edge[e];

        for (int i=0; i<e;i ++) {
            this.edges[i] = new Edge();
        }
    }

    int find (int parent[], int vertex) {
        if (parent[vertex] == -1) {
            return vertex;
        }
        return find (parent, parent[vertex]);
    }

    void union (int parent[], int u, int v) {
        parent[u] = v;
    }

    boolean isCyclic (UnionFind graph) {
        int parent[] = new int[this.v];

        for (int i=0; i<this.v; i++) {
            parent[i] = -1;
        }

        for (int i=0; i<this.e; i++) {
            int x = graph.find(parent, graph.edges[i].src);
            int y = graph.find(parent, graph.edges[i].dest);

            if (x == y) {
                return true;
            }

            graph.union(parent, x, y);
        }
        return false;
    }

    public static void main(String[] args) {
        UnionFind graph = new UnionFind(3, 3);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

        graph.edges[2].src = 2;
        graph.edges[2].dest = 0;

        System.out.println(graph.isCyclic(graph) ? "Cycle" : "No cycle");
    }
}
