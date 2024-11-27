package com.pankaj.java.graph;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class Topological {

    int vertices;
    ArrayList<Integer> adjListArray[];

    public Topological (int vertices) {
        this.vertices = vertices;
        this.adjListArray = new ArrayList[vertices];
        for (int i=0; i<vertices; i++) {
            adjListArray[i] = new ArrayList<>();
        }
    }

    public void addEdge (int u, int v) {
        adjListArray[u].add(v);
    }

    public void topologicalDFS () {
        Stack<Integer> stack = new Stack<>();

        boolean visited[] = new boolean[vertices];

        for (int i=0; i<vertices; i++) {
            if (visited[i] == false) {
                topologicalDFSUtil(i, visited, stack);
            }
        }
        System.out.println(stack.toString());
    }

    private void topologicalDFSUtil (int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        Iterator<Integer> itr = adjListArray[v].iterator();
        while (itr.hasNext()) {
            int u = itr.next();
            if (!visited[u]) {
                visited[u] = true;
                topologicalDFSUtil(u, visited, stack);
            }
        }
        stack.push(v);
    }

    //Kahn's algorithm
    private void topologicalBFS () {
        int[] indegree = new int[this.vertices];

        for (int i=0; i<this.vertices; i++) {
            ArrayList<Integer> edges = this.adjListArray[i];
            for (int node : edges) {
                indegree[node]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<this.vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int n : this.adjListArray[node]) {
                if (--indegree[n] == 0) {
                    queue.add(n);
                }
            }
            count++;
        }
        if (count != this.vertices) {
            System.out.println("Cycle exist");
            return;
        }

        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        Topological tp = new Topological(6);
        tp.addEdge(5, 2);
        tp.addEdge(5, 0);
        tp.addEdge(4, 0);
        tp.addEdge(4, 1);
        tp.addEdge(2, 3);
        tp.addEdge(3, 1);
        tp.topologicalDFS();
        tp.topologicalBFS();
    }

}
