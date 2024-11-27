package com.pankaj.java.misc;

import java.util.Iterator;
import java.util.LinkedList;

public class CourseSchedule {

    public boolean canFinish (int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] adj = new LinkedList[numCourses];
        boolean[] visited = new boolean[numCourses];

        for (int i=0; i<numCourses; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i=0; i<prerequisites.length; i++) {
            addEdge(adj, prerequisites[i][1], prerequisites[i][0]);
        }

        for (int i=0; i<numCourses; i++) {
            if (!visited[i]) {
                if (helper(adj, i, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean helper (LinkedList[] adj, int v, boolean[] visited) {
        visited[v] = true;
        Iterator<Integer> itr = adj[v].iterator();

        while (itr.hasNext()) {
            int u = itr.next();
            if (!visited[u]) {
                if (helper(adj, u, visited)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private void addEdge (LinkedList[] adj, int u, int v) {
        adj[u].add(v);
    }

    public static void main(String args[]) {
        int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        System.out.println(new CourseSchedule().canFinish(5, prerequisites));
    }
}
