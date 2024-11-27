package com.pankaj.java.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class GraphUsingSetHash {

    static HashMap<Integer, TreeSet<Integer>> graph;
    static int v;

    public GraphUsingSetHash () {
        graph = new HashMap<>();

        for (int i=0; i<v; i++) {
            graph.put(i, new TreeSet<>());
        }
    }

    static void addEdge (int src, int dest) {
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    static void printGraph () {
        for (int i=0; i<v; i++) {
            System.out.print("Adjacent list of vertex " + i + "-> ");
            Iterator<Integer> set = graph.get(i).iterator();

            while (set.hasNext()) {
                System.out.print(set.next() + " ");
            }

            System.out.println();
        }
    }

    static void searchEdge (int src, int dest) {
        Iterator<Integer> set = graph.get(src).iterator();
        if (graph.get(src).contains(dest)) {
            System.out.println("Edge found between the nodes " + src + " and " + dest);
        } else {
            System.out.println("No edge exists between the nodes " + src + " and " + dest);
        }
    }

    public static void main(String[] args) {
        v = 5;
        GraphUsingSetHash gst = new GraphUsingSetHash();
        addEdge(0, 1);
        addEdge(0, 4) ;
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 3);
        addEdge(3, 4);

        printGraph();

        searchEdge(2, 1);
        searchEdge(0, 3);
    }
}
