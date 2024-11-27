package com.pankaj.java.graph;

import java.util.ArrayList;
import java.util.List;

public class NLengthCycle {

    // https://www.geeksforgeeks.org/cycles-of-length-n-in-an-undirected-and-connected-graph/

    int vertices;
    int count;
    ArrayList<List> paths;

    NLengthCycle (int vertices) {
        this.vertices = vertices;
        this.count = 0;
        this.paths = new ArrayList<>();
    }

    int countCycles (int graph[][], int n) {
        boolean visited[] = new boolean[vertices];
        for (int i=0; i<vertices-n+1; i++) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(i);
            DFS(graph, visited, n-1, i, i, path);

            //Mark this vertex as visited. We have counted the cycles starting with this vertex
            visited[i] = true;
        }
        return count/2;
    }

    private void DFS (int graph[][], boolean visited[], int n, int src, int start, ArrayList<Integer> path) {
        visited[src] = true;

        if (n == 0) {
            // mark the vertex as unvisited to make it usable again
            visited[src] = false;

            //check if src end with vertex start, if it ends with start (adjacent node), it will form a cycle with given
            // length

            if (graph[src][start] == 1) {
                paths.add(new ArrayList(path));
                count++;
            }
            return;
        }

        // For searching every possible path of length n-1. (Search for every adjacent vertex of src)
        for (int i=0; i<vertices; i++) {
            if (!visited[i] && graph[src][i] == 1) {
                path.add(i);
                DFS(graph, visited, n-1, i, start, path);
                path.remove(new Integer(i));
            }
        }
        //mark src as unvisited to make it visible again
        visited[src] = false;
    }

    public static void main(String[] args) {
        NLengthCycle cycle = new NLengthCycle(5);
        int graph[][] = {{0, 1, 0, 1, 0},
                         {1, 0, 1, 0, 1},
                         {0, 1, 0, 1, 0},
                         {1, 0, 1, 0, 1},
                         {0, 1, 0, 1, 0}};

        int n = 4;

        System.out.println("Number of cycles : " + cycle.countCycles(graph, n));
        System.out.println("Paths : " + cycle.paths);
    }
}
