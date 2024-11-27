package com.pankaj.java.graph;

import java.util.Iterator;
import java.util.LinkedList;

//Directed Graph
public class DetectCycle {

    //Good Read - https://algorithms.tutorialhorizon.com/graph-detect-cycle-in-a-directed-graph/

    int vertices;
    LinkedList<LinkedList<Integer>> adj;

    public DetectCycle (int vertices) {
        this.vertices = vertices;
        this.adj = new LinkedList<>();

        for (int i=0; i<vertices; i++) {
            this.adj.add(new LinkedList<>());
        }
    }

    private void addEdge (int u, int v) {
        adj.get(u).add(v);
    }

    public boolean isCyclic () {
        boolean visited[] = new boolean[vertices];
        boolean recursiveStack[] = new boolean[vertices];

        for (int i=0; i<vertices; i++) {
            if (isCyclicUtil(i, visited, recursiveStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil (int src, boolean visited[], boolean recursiveStack[]) {
        //rec track the path which is being traversed, revisiting means same vertex on this path - cycle
        if (recursiveStack[src]) {
            return true;
        }
        //visited means the same vertex is being traversed again, not necessarily due to cycle, we have to ignore
        //because it will lead to the same visited path with no different result
        if (visited[src]) {
            return false;
        }
        visited[src] = true;
        recursiveStack[src] = true;

        Iterator<Integer> itr = adj.get(src).iterator();
        while (itr.hasNext()) {
            int v = itr.next();
            if(isCyclicUtil(v, visited, recursiveStack)) {
                return true;
            }
        }

        recursiveStack[src] = false;
        return false;
    }

    public static void main(String[] args) {
        DetectCycle graph = new DetectCycle(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        //graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        //graph.addEdge(3, 3);

        System.out.println((graph.isCyclic()) ? "Cycle found" : "No cycle found");
    }
}
