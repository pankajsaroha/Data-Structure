package com.pankaj.java.geeks;

import java.util.*;

public class ArrayProblems {

    public boolean keyInSegment (int [] arr, int n, int k) {
        boolean isPresent = false;
        for (int i=0; i<arr.length; i += 3) {
            boolean presentInSegment = false;
            for (int j=i; j< i+k; j++) {
                if(n == arr[j]) {
                    presentInSegment = true;
                    if(j >= arr.length - k) {
                        isPresent = true;
                        break;
                    }
                }
            }
            if(!presentInSegment) break;
        }
        return isPresent;
    }

    public OptionalInt getMax (int[] arr) {
        return Arrays.stream(arr).reduce(Integer::max);
    }

    public int getMin (int arr[]) {
        return Arrays.stream(arr).reduce(Integer::min).getAsInt();
    }

    public int findOccurences (int[] arr, int key) {
        int occurence = 0;
        for(int i=0; i<arr.length; i++) {
            if(key == arr[i]) occurence++;
        }
        return occurence;
    }

    public void rangeAndCoefficient (int[] arr) {
        float max = getMax(arr).getAsInt();
        float min = getMin(arr);

        System.out.println("Range: " + (max - min));

        float coe = (max - min) / (max + min);
        System.out.println("Coefficient: " + coe);
    }

    public int[] moveNegative (int[] arr) {
        int i = -1;
        int j = 0;
        boolean firstPositive = false;
        while(j < arr.length) {
            if(arr[j] >= 0 && !firstPositive) {
                i = j;
                firstPositive = true;
            }
            if(arr[j] < 0 && i >= 0) {
                swap(arr, i, j);
                int k = j;
                while (k >= 0){
                    if(arr[k] < 0) {
                        i = k + 1;
                        break;
                    }
                    k--;
                }
                j--;
            }
            j++;
        }
        return arr;
    }

    public int[] moveNegativeAside (int[] arr) {
        int i=0, j=arr.length - 1;
        while(i < j) {
            if(arr[i] >= 0 && arr[j] < 0) swap(arr, i, j);
            if(arr[i] < 0) i++;
            if(arr[j] > 0) j--;
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] cyclicRotate(int[] arr) {
        int last = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i+1] = arr[i];
        }
        arr[0] = last;
        return arr;
    }

    public int countPairsWithSum (int[] arr, int sum) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            if(!map.containsKey(arr[i])) map.put(arr[i], 0);
            map.put(arr[i], map.get(arr[i]) + 1);
        }

        for(int i=0; i<arr.length; i++) {
            if(map.containsKey(sum - arr[i])) count += map.get(sum - arr[i]);

            if(sum - arr[i] == arr[i]) count--;
        }
        return count/2;
    }

    public int getMissingNumber(int[] arr, int n) {
        int total = 1;
        for(int i = 2; i <= (n + 1); i++) {
            total += i;
            total -= arr[i - 2];
        }
        return total;
    }

    public void findDuplicate (int[] arr) {
        for (int i=0; i<arr.length; i++) {
            arr[arr[i] % arr.length] = arr[arr[i] % arr.length] + arr.length;
        }
        for (int i=0; i<arr.length; i++) {
            if(arr[i] >= arr.length * 2) {
                System.out.println(i +" ");
            }
        }
    }

    public void tripletWithSum (int arr[], int sum) {
        for(int i=0; i<arr.length-2; i++) {
            Set<Integer> set = new HashSet<>();
            int cur_sum = sum - arr[i];
            for(int j=i+1; j<arr.length; j++) {
                if(set.contains(cur_sum - arr[j])) {
                    System.out.printf("Triplet is %d, %d, %d", arr[i], arr[j], cur_sum-arr[j]);
                    return;
                }
                set.add(arr[j]);
            }
        }
    }

    public void intersection (int arr1 [], int arr2 [], int arr3 []) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                System.out.println(arr1[i]);
                i++; j++; k++;
            }
            else if (arr1[i] < arr2[j]) {
                i++;
            }
            else if (arr2[j] < arr3[k]){
                j++;
            } else {
                k++;
            }
        }
    }

    public void largestThree (int arr[]) {
        int i, first, second, third;
        first = second = third = Integer.MIN_VALUE;
        for (i=0; i<arr.length; i++) {
            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second) {
                third = second;
                second = arr[i];
            } else if (arr[i] > third) {
                third = arr[i];
            }
        }
        System.out.print("Largest Three : " +  first +" " + second + " " + third);
    }

    public boolean subarrayZeroSum (int arr[]) {

        Set<Integer> set = new HashSet<>();
        int sum = 0;

        for (int i=0; i<arr.length; i++) {
            sum += arr[i];

            if (arr[i] == 0 || sum == 0 || set.contains(sum)) return true;

            set.add(sum);
        }

        return false;
    }

    public int maxSubArraySum (int arr[]) {
        int max_so_far = arr[0];
        int current_max = arr[0];

        for(int i=1; i<arr.length; i++) {
            current_max = Math.max(arr[i], current_max + arr[i]);
            max_so_far = Math.max(max_so_far, current_max);
        }
        return max_so_far;
    }

    public void maxSubArraySumKandaneAlgo (int arr[]) {
        int max_so_far = Integer.MIN_VALUE;
        int max_ending_here = 0;
        int start = 0, end = 0, s = 0;

        for (int i=0; i<arr.length; i++) {
            max_ending_here += arr[i];

            if(max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }

            if(max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }

        System.out.println("Max: " + max_so_far +"\nStart: " + start + "\nend: " + end);
    }

    public int longestConsecutiveSubSequence (int arr[]) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;
        for (int i=0; i<arr.length; i++) {
            set.add(arr[i]);
        }

        for(int i=0; i<arr.length; i++) {
            //starting element
            if(!set.contains(arr[i]-1)) {
                int j = arr[i];
                while(set.contains(j)) {
                    j++;
                }

                if (longest < j - arr[i]) {
                    longest = j - arr[i];
                }
            }
        }
        return longest;
    }

    public int getMinDiff (int arr[], int n, int k) {
        if (n == 1) return 0;

        Arrays.sort(arr);

        int ans = arr[n-1] - arr[0];

        int small = arr[0] + k;
        int big = arr[n-1] - k;
        int temp = 0;

        if (small > big) {
            temp = small;
            small = big;
            big = temp;
        }

        for (int i=1; i<n-1; i++) {
            int subtract = arr[i] - k;
            int add = arr[i] + k;

            if (subtract >= small || add <= big) {
                continue;
            }

            if (big - subtract <= add - small) {
                small = subtract;
            } else {
                big = add;
            }
        }

        return Math.min(ans, big - small);
    }

    int gcd (int a, int b) {
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public int minJumpsRecursion (int arr[], int l, int h) {
        if (h == l)
            return 0;

        if (arr[l] == 0)
            return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        for(int i = l + 1; i <= h && i <= l + arr[l]; i++) {
            int jumps = minJumpsRecursion(arr, i, h);
            if (jumps != Integer.MAX_VALUE && jumps + 1 < min) {
                min = jumps + 1;
            }
        }
        return min;
    }

    public int minJumpsLoop (int arr[]) {
        int[] jumps = new int[arr.length];

        if(arr[0] == 0 || arr.length == 0) return Integer.MAX_VALUE;

        for(int i=0; i<arr.length; i++) jumps[i] = Integer.MAX_VALUE;

        jumps[0] = 0;

        for(int i=1; i<arr.length; i++) {
            for(int j=0; j<i; j++) {
                if(i <= j+arr[j] && jumps[j] != Integer.MAX_VALUE) {
                    jumps[i] = Math.min(jumps[i], jumps[j]+1);
                    break;
                }
            }
        }
        return jumps[arr.length-1];
    }

    public static void main(String[] args) {
        int arr[] = { 3, 5, 2, 4, 9, 3, 1, 7, 3, 11, 3, 4};
        ArrayProblems ap = new ArrayProblems();
        //System.out.println(ap.keyInSegment(arr, 3, 3));
        //System.out.println(Arrays.toString(ap.moveNegativeAside(new int[]{-12, 11, -13, -5, 6, -7, 5, -3, -6})));
        //int arr1[] = {1, 5, 7, -1, 5};
        //System.out.println(ap.countPairsWithSum(arr1, 6));
        int arr1[] = {1, 5, 10, 20, 40, 80};
        int arr2[] = {6, 7, 20, 80, 100};
        int arr3[] = {3, 4, 15, 20, 30, 70, 80, 120};
        int arr4[] = { 1, 4, 45, 6, 10, 8 };
        ap.tripletWithSum(arr4, 22);
    }
}
