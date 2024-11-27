package com.pankaj.java.array;

import java.util.Arrays;

public class MaxConsecutiveGap {

    /*
    * https://www.geeksforgeeks.org/maximum-adjacent-difference-array-sorted-form/
    * https://www.interviewbit.com/problems/maximum-consecutive-gap/
    * https://leetcode.com/problems/maximum-gap/discuss/1617881/Maximum-Gap-using-Bucket-java-O(n)-with-O(n)
    * https://stackoverflow.com/questions/10262937/array-maximum-difference-algorithm-that-runs-in-on
    */

    static int maxConsecutiveGap (int arr[]) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        int[] maxBucket = new int[arr.length - 1];
        int[] minBucket = new int[arr.length - 1];

        Arrays.fill(maxBucket, 0, arr.length-1, Integer.MIN_VALUE);
        Arrays.fill(minBucket, 0, arr.length-1, Integer.MAX_VALUE);

        //average gap from minNum to maxNum.
        float gap = (float) (max-min)/(float) (arr.length-1);

        /* Traversing through array elements and filling in appropriate bucket if bucket is empty.
        * Else updating bucket values.
        */
        for (int i=0; i < arr.length; i++) {
            if (arr[i] != min && arr[i] != max) {
                /*
                * Finding index in bucket
                */

                int index = (int) (Math.round((arr[i] - min) / gap));

                maxBucket[index] = (maxBucket[index] == Integer.MIN_VALUE) ? arr[i] : Math.max(maxBucket[index], arr[i]);

                minBucket[index] = (minBucket[index] == Integer.MAX_VALUE) ? arr[i] : Math.min(minBucket[index], arr[i]);
            }
        }


        /*
         * Finding Max Gap between maximum value of previous bucket and minimum value of next bucket
         * (Elements in the same bucket won't generate the maxgap (difference) more than gap
         */
        int prevValue = min;
        int maxGap = 0;

        for (int i=0; i<arr.length-1; i++) {
            if (minBucket[i] != Integer.MAX_VALUE) {
                maxGap = Math.max(maxGap, minBucket[i] - prevValue);
                prevValue = maxBucket[i];
            }
        }
        maxGap = Math.max(maxGap, max - prevValue);

        return maxGap;
    }

    public static void main(String[] args) {
        int arr[] = {1, 10, 5};
        System.out.println(maxConsecutiveGap(arr));
    }
}
