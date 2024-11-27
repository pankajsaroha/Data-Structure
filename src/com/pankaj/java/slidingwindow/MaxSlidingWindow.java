package com.pankaj.java.slidingwindow;

import java.util.*;

public class MaxSlidingWindow {

    public static List<Integer> maxSlidingWindow (int arr[], int k) {
        List<Integer> list = new ArrayList<>();

        Deque<Integer> deque = new LinkedList<>();

        for (int i=0; i < arr.length; i++) {

            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }

            deque.addLast(i);

            if (i >= k-1) {
                list.add(arr[deque.peekFirst()]);
            }
        }

        return list;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int n = nums.length;
        int res[] = new int[n-k+1];
        int index = 0;
        for (int i=0; i<n; i++) {

            if(i >= k && dq.peekFirst() == nums[i-k]) {
                dq.pollFirst();
            }

            while (!dq.isEmpty() && dq.peekLast() < nums[i]) {
                dq.pollLast();
            }

            dq.addLast(nums[i]);

            if (i >= k-1) {
                res[index++] = dq.peekFirst();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        System.out.println(maxSlidingWindow(arr, 3).toString());
        System.out.println(Arrays.toString(maxSlidingWindow2(arr, 3)));
    }
}
