package com.pankaj.java.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MinOpsLIS1713 {

    public static int minOperations(int[] target, int[] arr) {
        /*
         * can be solved using LCS but will give TLE
         *
         * Read first comment to understand -
         * https://leetcode.com/problems/minimum-operations-to-make-a-subsequence/discuss/999153/JavaC%2B%2BPython-LCS-to-LIS
         */

        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<target.length; i++) {
            map.put(target[i], i);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (!map.containsKey(a)) continue;
            if (list.size() == 0 || map.get(a) > list.get(list.size() - 1)) {
                list.add(map.get(a));
                continue;
            }
            int left = 0, right = list.size() - 1, mid;
            while (left < right) {
                mid = (left + right) / 2;
                if (list.get(mid) < map.get(a))
                    left = mid + 1;
                else
                    right = mid;
            }
            list.set(left, map.get(a));
        }
        return target.length - list.size();
    }

    public static void main(String[] args) {
        int target[] = {6,4,8,1,3,2};
        int arr[] = {4,7,6,2,3,8,6,1};

        System.out.println(minOperations(target, arr));
    }
}
