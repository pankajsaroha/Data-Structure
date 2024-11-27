package com.pankaj.java.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class GraphPractice {

    public static void main(String[] args) {
        GraphPractice graphPractice = new GraphPractice();

        int inter[][] = {{6, 8}, {1, 9}, {2, 4}, {4, 7}};
        Stack<int[]> stack = new Stack<>();

        Arrays.sort(inter, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        stack.push(inter[0]);

        for (int i=1; i<inter.length; i++) {
            if (stack.peek()[1] > inter[i][0]) {
                int arr[] = stack.pop();
                arr[1] = arr[1] > inter[1][1] ? arr[1] : inter[1][1];
                stack.push(arr);
            } else {
                stack.push(inter[i]);
            }
        }

        for (int arr[] : stack) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
