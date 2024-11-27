package com.pankaj.java.matrix;

import java.util.Arrays;

public class RotateMatrix {
    public void rotate(int arr[][]) {
        int n = arr.length;

        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n/2; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][n-1-j];
                arr[i][n-1-j] = temp;
            }
        }

        for(int[] l : arr) {
            System.out.println(Arrays.toString(l));
        }
    }

    public static void main(String[] args) {
        RotateMatrix t = new RotateMatrix();
        int arr[][] = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        t.rotate(arr);
    }
}
