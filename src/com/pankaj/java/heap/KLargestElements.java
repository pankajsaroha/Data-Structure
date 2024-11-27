package com.pankaj.java.heap;

import java.util.Arrays;

public class KLargestElements {

    int[] kLargest(int[] arr, int n, int k) {

        int res[] = new int[k];
        int size = 0;
        for (int i=0; i<k; i++) {
            insert (res, arr[i], size++);
        }
        for (int i=k; i<n; i++) {
            if (arr[i] > res[0]) {
                res[0] = arr[i];
                heapify (res, 0, size);
            }
        }
        Arrays.sort(res);
        for (int i=0, j=k-1; i<j; i++, j--) {
            int temp = res[i];
            res[i] = res[j];
            res[j] = temp;
        }
        return res;
    }

    private void insert (int res[], int data, int size) {
        res[size++] = data;
        for (int i=size/2-1; i>=0; i--) {
            heapify (res, i, size);
        }
    }

    private void heapify (int res[], int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && res[smallest] > res[left]) {
            smallest = left;
        }
        if (right < size && res[smallest] > res[right]) {
            smallest = right;
        }
        if (smallest != i) {
            int temp = res[smallest];
            res[smallest] = res[i];
            res[i] = temp;

            heapify(res, smallest, size);
        }
    }

    public static void main(String[] args) {
        KLargestElements kl = new KLargestElements();
        int arr[] = {1, 23, 12, 9, 30, 2, 50};
        System.out.println(Arrays.toString(kl.kLargest(arr, 7, 3)));
    }
}
