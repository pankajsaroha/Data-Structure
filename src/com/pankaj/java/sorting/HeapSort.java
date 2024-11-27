package com.pankaj.java.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort {

    static void sort (int arr[]) {
        int n = arr.length;

        /*
        * Build max heap
        */
        for (int i=n/2-1; i>=0; i--) {
            heapify(arr, n, i);
        }

        /*
        * Sort the array
        */
        for (int i=n-1; i>=0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify (int arr[], int n, int i) {
        int left = 2*i + 1;
        int right = 2*i + 2;
        int largest = i;

        if (left < n && arr[largest] < arr[left]) {
            largest = left;
        }
        if (right < n && arr[largest] < arr[right]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;

            heapify(arr, n, largest);
        }
    }

    static void print(int arr[]) {
        for(Integer i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        String a[] = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }
        sort(arr);
        print(arr);
    }
}
