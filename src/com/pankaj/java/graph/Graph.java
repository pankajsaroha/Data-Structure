package com.pankaj.java.graph;

import java.util.LinkedList;

public class Graph {

    int V;
    LinkedList<Integer> adjListArray[];

    Graph (int V) {
        this.V = V;
        adjListArray = new LinkedList[V];

        for (int i=0; i<V; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }
}
