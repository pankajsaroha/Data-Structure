package com.pankaj.java;

import java.util.Arrays;

public class Test {

    public static int longestOnes (int[] arr, int k) {
        int i=0, j=0, res=0;
        for (j=0; j<arr.length; j++) {
            if (arr[j] == 0) {
                k--;
            }
            if (k < 0 && arr[i++] == 0) {
                k++;
            }
        }
        return j-i;
    }


    public int solution(int[] A, int[] B) {

        int n = A.length;
        long leftA = 0, rightA = 0, leftB = 0, rightB = 0, sum1 = 0, sum2 = 0;
        int fair = 0;

        for (int i=0; i<n; i++) {
            sum1 += A[i];
            sum2 += B[i];
        }

        for (int i=1; i<n; i++) {
            leftA = leftA + A[i-1];
            rightA = sum1 - leftA;

            leftB = leftB + B[i-1];
            rightB = sum2 - leftB;

            if (leftA == leftB && leftA == rightA && leftA == rightB) fair++;
        }
        return fair;
    }

    public int solution(int[] blocks) {
        int n = blocks.length, distance = 0;
        for (int start = 0; start < n; start++) {
            int j = start, k = start;
            if (start == 0 || blocks[start] < blocks[start - 1]) {
                while (j > 0 && blocks[j - 1] >= blocks[j]) {
                    j--;
                }
                while (k < n - 1 && blocks[k] <= blocks[k + 1]) {
                    k++;
                }
                distance = Math.max(distance, k - j + 1);
            }
        }
        return distance;
    }

    public int solution2(int[] blocks) {
        int n = blocks.length;
        int[] j = new int[n];
        int[] k = new int[n];

        for (int i=0; i<n; i++) {
            k[i] = i;
            if (i > 0 && blocks[i] >= blocks[i-1]) {
                k[i] = k[i-1];
            }
        }
        for (int i=n-1; i>=0; i--) {
            j[i] = i;
            if (i < n-1 && blocks[i] >= blocks[i+1]) {
                j[i] = j[i+1];
            }
        }
        int distance = 0;
        for (int i=0; i<n; i++) {
            distance = Math.max(distance, k[i] - j[i] + 1);
        }
        return distance;
    }
    public static void main(String[] args) throws InterruptedException {
        //System.out.println(longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
        //int[] A = {2, 6, 8, 5};
        int[] A = {1, 5, 5, 2, 6};
        System.out.println(new Test().solution(A));

    }
}
