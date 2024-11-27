package com.pankaj.java.heap;

import java.util.Arrays;

public class MinHeap {

    public void insert (int arr[], int data, int n) {
        arr[n++] = data;
        for (int i=n/2-1; i>=0; i--) {
            heapify(arr, i, n);
        }
    }

    private void heapify (int arr[], int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < n && arr[smallest] > arr[left]) {
            smallest = left;
        }
        if (right < n && arr[smallest] > arr[right]) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;

            heapify(arr, smallest, n);
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        int n = 0;

        for (int data : arr) {
            minHeap.insert(arr, data, n++);
        }

        System.out.println(Arrays.toString(arr));
    }
}
