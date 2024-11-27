package com.pankaj.java.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS_DFS {

    /*
    * https://github.com/pankajsaroha/Java8Features/blob/master/JavaQueries/src/org/java/graph/Graph_BFS.java
    */
    static int V = 4;
    static Graph graph;

    static void addEdge (Graph graph, int u, int v) {
        graph.adjListArray[u].add(v);
    }

    static void BFS (int source) {
        boolean visited [] = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            source = queue.poll();
            System.out.print(source + " ");

            Iterator<Integer> itr = graph.adjListArray[source].iterator();

            while (itr.hasNext()) {
                int n = itr.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    static void DFS (int s) {
        boolean visited[] = new boolean[V];

        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;

        while (!stack.isEmpty()) {
            s = stack.pop();
            System.out.print(s + " ");
            Iterator<Integer> itr = graph.adjListArray[s].iterator();
            while (itr.hasNext()) {
                int n = itr.next();
                if (!visited[n]) {
                    visited[n] = true;
                    stack.push(n);
                }
            }
        }
    }

    static void DFSRecursive (int s) {
        boolean visited[] = new boolean[V];
        DFSUtil(s, visited);
    }

    static void DFSUtil (int s, boolean visited[]) {
        visited[s] = true;
        System.out.print(s + " ");

        Iterator<Integer> itr = graph.adjListArray[s].iterator();
        while (itr.hasNext()) {
            int n = itr.next();
            if(!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    public static void main(String[] args) {
        graph = new Graph(V);

        addEdge(graph,0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 3);

        System.out.print("Following is the breadth first traversal (starting from vertax 2): ");
        BFS(2);

        System.out.print("\nFollowing is the depth first traversal (starting from vertax 2): ");
        DFS(2);

        System.out.print("\nFollowing is the depth first traversal (starting from vertax 2) Recursive: ");
        DFSRecursive(2);
    }
}
