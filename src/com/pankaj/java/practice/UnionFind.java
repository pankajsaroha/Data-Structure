package com.pankaj.java.practice;

import java.util.Map;
import java.util.HashMap;

public class UnionFind<T>
{
    private static class Edge<T> {
        T src;
        T dest;
    }

    int v, e;
    Edge<T>[] edges;

    public UnionFind (int v, int e) {
        this.v = v;
        this.e = e;

        edges = new Edge[e];
        for (int i=0; i<e; i++) {
            this.edges[i] = new Edge<>();
        }
    }

    public T find(Map<T, T> parent, T vertex) {
        if (parent.get(vertex) == null) {
            return vertex;
        }
        return find (parent, parent.get(vertex));
    }

    public void union(Map<T, T> parent, T u, T v) {
        parent.put(u, v);
    }

    public boolean isCyclic(UnionFind graph) {
        Map<T, T> parent = new HashMap<>();

        for (int i=0; i<e; i++) {
            T x = find(parent, (T) graph.edges[i].src);
            T y = find (parent, (T) graph.edges[i].dest);

            if (x == y) {
                return true;
            }
            union(parent, x, y);
        }
        return false;
    }

    public static void main(String[] args) {
        UnionFind<Integer> graph = new UnionFind<>(3, 3);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

        graph.edges[2].src = 2;
        graph.edges[2].dest = 0;

        System.out.println(graph.isCyclic(graph));
    }
}