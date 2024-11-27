package com.pankaj.java.stack;

import java.util.Arrays;
import java.util.Comparator;

public class SimplifyPath {

    int index = 0;
    int[][] solution (int[][] coordinates) {
        Arrays.sort(coordinates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        for (int i=1; i<coordinates.length; i++) {
            if (coordinates[index][1] >= coordinates[i][0]) {
                coordinates[index][1] = Math.max(coordinates[index][1], coordinates[i][1]);
                int end = Math.max(coordinates[index][1], coordinates[i][1]);
            } else {
                index++;
                coordinates[index] = coordinates[i];
            }
        }
        return coordinates;
    }

    int trip (int n, int cabTripTime[]) {
        Arrays.sort(cabTripTime);
        int cab = 0;
        int dp[] = new int[cabTripTime.length];
        for (int i=0; i<cabTripTime.length; i++) {
            int j = 0;
            while (j <= i) {
                cab = cab + (cabTripTime[i]/cabTripTime[j]);
                j++;
            }
            dp[i] = cab;
            if (n <= cab) {
                int a = cabTripTime[i--];

                return cabTripTime[i];
            } else {
                cab = 0;
            }
        }
        int time = 0;
        int trip = 0;
        int i=0;
        while (n >= trip + dp[i]) {
            i++;
            if (i >= dp.length)  {
                time = time + cabTripTime[--i];
                trip = trip + dp[i];
                i = 0;
            }
        }
        if (i == 0) {
            return time;
        } else {
            return time + cabTripTime[--i];
        }
    }

    public static void main(String[] args) {
        SimplifyPath s = new SimplifyPath();
        int arr[][] = {{4, 7}, {-1, 5}, {3, 6}};
        arr = s.solution(arr);
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        int count = 0;
        for (int i=0; i<= s.index; i++) {
            count = count + arr[i][1] - arr[i][0] + 1;
        }
        System.out.println(count);

        int cabTripTime[] = {50, 6, 16, 48, 23, 11, 16, 46, 17, 22, 32, 41, 25, 5};
        System.out.println(s.trip(25, cabTripTime));
    }
}
