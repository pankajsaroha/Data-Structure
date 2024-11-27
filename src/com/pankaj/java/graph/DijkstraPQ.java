/*
package com.pankaj.java.graph;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DijkstraPQ {

    //https://algorithms.tutorialhorizon.com/dijkstras-shortest-path-algorithm-spt-adjacency-list-and-priority-queue-java-implementation/

    static class Edge {
        int source;
        int dest;
        int weight;

        public Edge (int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    int vertices;
    LinkedList<Edge>[] adjList;

    DijkstraPQ (int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];

        for (int v=0; v<vertices; v++) {
            adjList[v] = new LinkedList<>();
        }
    }

    private void addEdge (int source, int dest, int weight) {
        Edge edge = new Edge(source, dest, weight);
        adjList[source].add(edge);

        // For undirected graph
        edge = new Edge(dest, source, weight);
        adjList[dest].add(edge);
    }

    public void dijkstraPriorityQueue (int src) {
        int dist[] = new int[vertices];
        boolean spt[] = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);

        //Initialize priority queue and override the comparator to do the sorting based on key
        //Pair is (distance[vertex], vertex)

        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });

        //create pair for the first index, index=0 distance=0
        dist[0] = 0;
        Pair<Integer, Integer> pair = new Pair<>(dist[0], 0);
        priorityQueue.add(pair);

        while (!priorityQueue.isEmpty()) {
            pair = priorityQueue.poll();
            int u = pair.getValue();  // extract vertex with min distance. Same as minDistance() in Dijkstra.java

            if (spt[u] == false) {
                spt[u] = true;

                //Iterate through all the adjacent vertices and update the keys
                LinkedList<Edge> list = adjList[u];
                for (int i=0; i<list.size(); i++) {
                    Edge edge = list.get(i);
                    int v = edge.dest;

                    if(spt[v] == false) {
                        if (dist[v] > dist[u] + edge.weight) {
                            priorityQueue.offer(new Pair<>(dist[u] + edge.weight, v));
                            dist[v] = dist[u] + edge.weight;
                        }
                    }
                }
            }
        }
        printShortestDistance(dist, src);
    }

    private void printShortestDistance (int dist[], int src) {
        System.out.println("Vertex distance from source");
        for (int v=0; v<vertices; v++) {
            System.out.println("Source vertex: " + src + "to dest vertex " + v + " distance: " + dist[v]);
        }
    }

    public static void main(String[] args) {
        int vertices = 9;
        DijkstraPQ graph = new DijkstraPQ(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 5, 4);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        graph.dijkstraPriorityQueue(0);
    }
}
*/
