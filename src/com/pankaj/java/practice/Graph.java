package com.pankaj.java.practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    int V;
    LinkedList<Integer> adjList[];

    Graph (int V) {
        this.V = V;
        this.adjList = new LinkedList[V];

        for (int i=0; i<V; i++) {
            this.adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge (int src, int dest) {
        this.adjList[src].add(dest);
    }

    public void BFS (int src) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[this.V];
        queue.add(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            src = queue.poll();
            System.out.print(src + " ");
            Iterator<Integer> itr = this.adjList[src].iterator();
            while (itr.hasNext()) {
                int n = itr.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public void DFS (int src) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        stack.push(src);
        visited[src] = true;

        while (!stack.isEmpty()) {
            src = stack.pop();
            System.out.print(src + " ");
            Iterator<Integer> itr = this.adjList[src].iterator();
            while (itr.hasNext()) {
                int n = itr.next();
                if (!visited[n]) {
                    visited[n] = true;
                    stack.push(n);
                }
            }
        }
    }

    public void DFS_rec (int src) {
        boolean[] visited = new boolean[V];
        DFSUtil(src, visited);
    }

    public void DFSUtil (int src, boolean[] visited) {
        visited[src] = true;
        System.out.print(src + " ");

        Iterator<Integer> itr = this.adjList[src].iterator();
        while (itr.hasNext()) {
            int n = itr.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    public boolean detectCycle () {
        boolean[] visited = new boolean[V];
        boolean[] recursive = new boolean[V];

        for (int i=0; i<V; i++) {
            if (isCyclic(i, visited, recursive)) return true;
        }
        return false;
    }

    private boolean isCyclic (int src, boolean[] visited, boolean[] recursive) {
        if (recursive[src])
            return true;

        if (visited[src])
            return false;

        visited[src] = true;
        recursive[src] = true;

        Iterator<Integer> itr = this.adjList[src].iterator();
        while (itr.hasNext()) {
            int n = itr.next();
            if (isCyclic(n, visited, recursive)) {
                return true;
            }
        }
        recursive[src] = false;
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        System.out.println(graph.detectCycle());
    }
}
