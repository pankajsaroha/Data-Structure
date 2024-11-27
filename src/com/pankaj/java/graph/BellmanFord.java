package com.pankaj.java.graph;

import java.util.Arrays;
import java.util.LinkedList;

public class BellmanFord {

    static class Edge {
        int src;
        int dest;
        int weight;

        public Edge () {
            this.src = 0;
            this.dest = 0;
            this.weight = 0;
        }
    }

    int vertices;
    int edges;
    Edge edge[];

    public BellmanFord (int edges) {
        this.edges = edges;
        this.edge = new Edge[edges];

        for (int i=0; i<edges; i++) {
            edge[i] = new Edge();
        }
    }

    public void bellmanFord (BellmanFord graph, int src) {
        int dist[] = new int[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        // Relax all edges, the most number of edges between source to any vertex can be V-1
        for (int i=0; i<vertices-1; i++) {
            for (int j=0; j<edges; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int w = graph.edge[j].weight;

                if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        //Check negative cycle
        //If value changes then we have a negative cycle in the graph and shortest path can't be find
        for (int i=0; i<edges; i++) {
            int u = graph.edge[i].src;
            int v = graph.edge[i].dest;
            int w = graph.edge[i].weight;

            if (dist[v] > dist[u] + w) {
                System.out.println("Negative cycle found. Can't find the shortest path.");
                return;
            }
        }
        printSolution(dist, src);
    }

    void printSolution (int dist[], int src) {
        System.out.println("Vertex distance from source " + src);
        for (int i=0; i<vertices; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static void main(String[] args) {
        BellmanFord graph = new BellmanFord(8);
        graph.vertices = 5;

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.bellmanFord(graph, 0);
    }
}
