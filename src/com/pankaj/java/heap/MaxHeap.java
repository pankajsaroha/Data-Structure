package com.pankaj.java.heap;

import java.util.Arrays;

public class MaxHeap {

    public void insert (int arr[], int data, int n) {
        arr[n] = data;
        for (int i= arr.length/2 - 1; i>=0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    private void heapify (int heap[], int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < n && heap[largest] < heap[left]) {
            largest = left;
        }
        if (right < n && heap[largest] < heap[right]) {
            largest = right;
        }

        if (largest != i) {
            int temp = heap[largest];
            heap[largest] = heap[i];
            heap[i] = temp;

            heapify(heap, largest, n);
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = 0;

        for (int data : arr) {
            maxHeap.insert(arr, data, n++);
        }

        System.out.println(Arrays.toString(arr));
    }
}
