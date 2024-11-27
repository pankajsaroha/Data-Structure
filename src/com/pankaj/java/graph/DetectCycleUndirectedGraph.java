package com.pankaj.java.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class DetectCycleUndirectedGraph {

    int vertices;
    LinkedList<LinkedList<Integer>> adj;

    DetectCycleUndirectedGraph (int vertices) {
        this.vertices = vertices;
        this.adj = new LinkedList<>();

        for (int i=0; i<vertices; i++) {
            this.adj.add(new LinkedList<>());
        }
    }

    private void addEdge (int u, int v) {
        this.adj.get(u).add(v);
        this.adj.get(v).add(u);
    }

    public boolean isCyclic () {
        boolean visited[] = new boolean[vertices];

        for (int i=0; i<vertices; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil (int u, boolean visited[]) {
        visited[u] = true;
        Iterator<Integer> itr = adj.get(u).iterator();

        while (itr.hasNext()) {
            int v = itr.next();
            if (!visited[v]) {
                if (isCyclicUtil(v, visited)) {
                    return true;
                }
            }
            // check if adjacent is not the parent node
            // If adjacent is visited and not the parent of current, then there is a cycle.
            if (v != u) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Create a graph given
        // in the above diagram
        DetectCycleUndirectedGraph g1 = new DetectCycleUndirectedGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        if (g1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        DetectCycleUndirectedGraph g2 = new DetectCycleUndirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        if (g2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
    }
}
