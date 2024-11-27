package com.pankaj.java.sorting;

import java.util.Arrays;

public class QuickSort {

    private int partition (int arr[], int l, int h) {
        int pivot = h;
        int i = l-1;
        for (int j=l; j<h; j++) {
            if (arr[j] < arr[pivot]) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, pivot);
        return i;
    }

    public void quickSort (int arr[]) {
        quick(arr, 0, arr.length-1);
    }

    private void quick (int arr[], int l, int h) {
        if (l < h) {
            int pivot = partition(arr, l, h);
            quick(arr, l, pivot-1);
            quick(arr, pivot+1, h);
        }
    }

    private void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int arr[] = {5, 3, 1, 8, 4, 9};
        quickSort.quickSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
