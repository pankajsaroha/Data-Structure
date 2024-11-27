package com.pankaj.java.matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Pascal {
    public int[][] pascal1(int n) {
        ArrayList<int[]> list = new ArrayList<>();
        int pascal[] = new int[1];
        pascal[0] = 1;
        list.add(pascal);
        for(int i=1; i<n; i++) {
            pascal = new int[i+1];
            pascal[0] = 1;
            for (int j=1; j<i; j++) {
                pascal[j] = list.get(i-1)[j] + list.get(i-1)[j-1];
            }
            pascal[i] = 1;
            list.add(pascal);
        }
        int pas[][] = new int[n][n];
        for (int i=0; i<n; i++) {
            pas[i] = list.get(i);
        }
        return pas;
    }

    public ArrayList<ArrayList<Integer>> pascal2(int n) {
        ArrayList<ArrayList<Integer>> pascal = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (n == 0) return pascal;
        list.add(1);
        pascal.add(list);
        if (n == 1) return pascal;
        for(int i=1; i<n; i++) {
            list = new ArrayList<>();
            list.add(1);
            pascal.add(list);
            for (int j=1; j<i; j++) {
                pascal.get(i).add(j, pascal.get(i-1).get(j) + pascal.get(i-1).get(j-1));
            }
            pascal.get(i).add(1);
        }
        return pascal;
    }

    public static void main(String[] args) {
        Pascal p = new Pascal();
        int pascal [][] = p.pascal1(5);
        for (int[] arr : pascal) {
            System.out.println(Arrays.toString(arr));
        }

        ArrayList<ArrayList<Integer>> pascal2 = p.pascal2(5);
        for (ArrayList list : pascal2) {
            System.out.println(list.toString());
        }
    }
}
