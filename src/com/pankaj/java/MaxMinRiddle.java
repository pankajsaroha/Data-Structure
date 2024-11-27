package com.pankaj.java;

import java.util.*;

public class MaxMinRiddle {

    public long[] riddle(long arr[]) {
        int n = arr.length;
        long[] result = new long[arr.length];
        long max = 0;
        long[] dp = new long[arr.length];
        for (int i=0; i<n; i++) {
            result[i] = maxSlidingWindow(arr, i+1);
        }
        return result;
    }

    private long maxSlidingWindow(long arr[], int k) {
        Deque<Long> dq = new LinkedList<>();
        long max = 0;
        int index = 0;
        for (int i=0; i<arr.length; i++) {

            if (i >= k && dq.peekFirst() == arr[i-k]) {
                dq.pollFirst();
            }

            while (!dq.isEmpty() && dq.peekLast() > arr[i]) {
                dq.pollLast();
            }

            dq.add(arr[i]);

            if (i >= k-1) {
                max = Math.max(max, dq.peekFirst());
            }
        }
        return max;
    }




    public static void main(String[] args) {
        MaxMinRiddle t = new MaxMinRiddle();

        long arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        System.out.println(t.maxSlidingWindow(arr, 3));

        /*for (List<String> l : result) {
            System.out.println(l);
        }*/

        //System.out.println(Arrays.toString(t.riddle(new long[] {6, 3, 5, 1, 12})));

    }
}
