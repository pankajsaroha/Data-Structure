package com.pankaj.java.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Kruskal_MinimumSpanningTree {
    int vertices;
    List<List<Edge>> graph;

    public Kruskal_MinimumSpanningTree(int vertices) {
        this.vertices = vertices;
        this.graph = new ArrayList<>();
        for (int i=0; i<vertices; i++) {
            this.graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        graph.get(u).add(new Edge(u, v, w));
    }

    public List<Edge> minimumSpanningTree () {
        /**
         * Sort the graph with weights, the graph is list<list<edges>> and can't be sorted directly, hence will
         * flatten the graph into List<Edges> and sort.
          */
        //lambada
        //graph.stream().flatMap(edges -> edges.stream()).collect(Collectors.toList());
        //method reference
        List<Edge> edges = graph.stream().flatMap(List::stream).collect(Collectors.toList());
        //this sorting makes sure the tree is minimum spanning
        Collections.sort(edges, ((e1, e2) -> e1.weight - e2.weight));

        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(vertices);

        for (Edge edge : edges) {
            int u = uf.find(edge.src);
            int v = uf.find(edge.dest);

            if (u != v) {
                mst.add(edge);
                uf.union(u, v);

                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }

    public static void main (String args[]) {
        int vertices = 6; // Number of vertices
        Kruskal_MinimumSpanningTree graph = new Kruskal_MinimumSpanningTree(vertices);

        // Adding edges (src, dest, weight)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 0, 4);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 3);
        graph.addEdge(5, 4, 3);

        // Run Kruskal's algorithm to find the MST
        for (Edge edge : graph.minimumSpanningTree()) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }

    class UnionFind {
        int vertices;
        int[] parent, rank;

        public UnionFind(int vertices) {
            parent = new int[vertices];
            rank   = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
            }
        }

        public int find (int u) {
            if (parent[u] == u) {
                return u;
            }
            return parent[u] = find(parent[u]);
        }

        public void union (int u, int v) {
            int parentU = find(u);
            int parentV = find(v);

            if (parentU != parentV) {
                if (parent[parentU] > parent[parentV]) {
                    parent[parentV] = parentU;
                } else if (parent[parentU] < parent[parentV]) {
                    parent[parentU] = parentV;
                } else {
                    parent[parentU] = parentV;
                    rank[parentV]++;
                }
            }
        }
    }

    class Edge {
        int src;
        int dest;
        int weight;

        public Edge (int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
