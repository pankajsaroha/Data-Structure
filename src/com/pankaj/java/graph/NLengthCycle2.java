package com.pankaj.java.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class NLengthCycle2 {

    //Original Solution 0 - NLengthCycle

    int vertices;
    int count;
    int n;
    ArrayList<Integer> adj[];
    ArrayList<ArrayList<Integer>> paths;

    NLengthCycle2 (int vertices, int n) {
        this.vertices = vertices;
        this.n = n;
        this.adj = new ArrayList[vertices];
        this.paths = new ArrayList<>();

        for (int i=0; i<vertices; i++) {
            this.adj[i] = new ArrayList<>();
        }
    }

    private void addEdge (int u, int v) {
        this.adj[u].add(v);
        this.adj[v].add(u);
    }

    int DFS () {
        boolean visited[] = new boolean[vertices];
        ArrayList<Integer> path;
        for (int i=0; i<vertices-n+1; i++) {
            path = new ArrayList<>();
            int length = 0;
            path.add(i);
            count = DFSUtil(i, visited, length, path);
            visited[i] = true;
        }
        return count/2;
    }

    int DFSUtil (int src, boolean visited[], int length, ArrayList<Integer> path) {
        visited[src] = true;
        Iterator<Integer> itr = this.adj[src].iterator();
        while (itr.hasNext()) {
            int v = itr.next();
            if(!visited[v]) {
                path.add(v);
                DFSUtil(v, visited, ++length, path);
                path.remove(new Integer(v));
                visited[v] = false;
                --length;
            }

            if (v != src && length == n-1) {
                visited [src] = false;
                paths.add(new ArrayList<>(path));
                return ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NLengthCycle2 graphPractice = new NLengthCycle2(5, 4);

        graphPractice.addEdge(0, 1);
        graphPractice.addEdge(0, 3);
        graphPractice.addEdge(1, 2);
        graphPractice.addEdge(1, 4);
        graphPractice.addEdge(2, 3);
        graphPractice.addEdge(3, 4);


        System.out.println("Number of cycles: " + graphPractice.DFS());
        System.out.println(graphPractice.paths);
    }
}
