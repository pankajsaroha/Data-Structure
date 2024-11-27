package com.pankaj.java.misc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class CourseSchedule2 {

    private void addEdge (LinkedList[] adj, int u, int v) {
        adj[u].add(v);
    }

    private void topological (LinkedList[] adj, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        Iterator<Integer> itr = adj[v].iterator();
        while (itr.hasNext()) {
            int u = itr.next();
            if (!visited[u]) {
                topological(adj, u, visited, stack);
            }
        }
        stack.push(v);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] adj = new LinkedList[numCourses];

        for (int i=0; i<numCourses; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i=0; i<numCourses; i++) {
            if (prerequisites.length > 0 && prerequisites[i].length > 1)
                addEdge(adj, prerequisites[i][1], prerequisites[i][0]);
        }

        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[numCourses];

        for (int i=0; i<numCourses; i++) {
            if (visited[i] == false)
                topological(adj, i, visited, stack);
        }

        int res[] = new int[stack.size()];
        int index = 0;

        while (!stack.isEmpty()) {
            res[index++] = stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        CourseSchedule2 t = new CourseSchedule2();
        System.out.println(Arrays.toString(t.findOrder(4, new int[][] {
                {1,0},{2,0},{3,1},{3,2}
        })));

        new LinkedList<>().add(5);
    }
}
