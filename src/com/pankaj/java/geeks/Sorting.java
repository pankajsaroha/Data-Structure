package com.pankaj.java.geeks;

import com.pankaj.java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sorting {

    public int[] bubble (int [] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]) swap(arr, i, j);
            }
        }
        return arr;
    }

    public int [] selection (int [] arr) {
        for(int i = 0; i < arr.length; i++) {
            int min = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[min]) min = j;
            }
            swap(arr, i, min);
        }
        return arr;
    }

    public int [] insertion (int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr [j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr;
    }

    public int[] quick(int arr[], int l, int h) {
        if(l < h) {
            int pivot = partition(arr, l, h);
            quick(arr, l, pivot - 1);
            quick(arr, pivot + 1, h);
        }
        return arr;
    }

    public int[] merge(int arr[], int l, int r) {
        if(l < r) {
            int m = (l + r) / 2;
            merge(arr, l, m);
            merge(arr, m + 1, r);

            mergeSort(arr, l, m, r);
        }
        return arr;
    }

    private void mergeSort(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i=0; i < n1; i++) left[i] = arr[l + i];
        for(int j=0; j < n2; j++) right[j] = arr[m + 1 +j];

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if(left[i] < right[j])  {
                arr[k] = left[i];
                i++; k++;
            } else {
                arr[k] = right[j];
                j++; k++;
            }
        }

        while(i < n1) {
            arr[k] = left[i];
            i++; k++;
        }

        while(j < n2) {
            arr[k] = right[j];
            j++; k++;
        }
    }

    private int partition (int arr[], int l, int h) {
        int pivot = h;
        int i = l - 1;
        for(int j = l; j < h; j++) {
            if(arr[j] < arr[pivot]) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, h);
        return i+1;
    }

    public void swap (int [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void treeSort(int[] arr) {
        Tree tree = new Tree();
        for(int i=0; i<arr.length; i++) {
            tree.insert(arr[i]);
        }
        tree.inOrder(tree.getRoot());
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] arr = new int[n];
        String input [] = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Sorting srt = new Sorting();
        //System.out.println(Arrays.toString(srt.insertion(arr)));
        //System.out.println(Arrays.toString(srt.merge(arr, 0, arr.length - 1)));
        srt.treeSort(arr);
    }
}
