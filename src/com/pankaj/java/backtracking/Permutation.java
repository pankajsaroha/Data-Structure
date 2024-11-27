package com.pankaj.java.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

    /*
    * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    * */

    static void permute (String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
        } else {
            for (int i=l; i<=r; i++) {
                str = swap(str, l, i);
                permute(str, l+1, r);
                str = swap(str, l, i);
            }
        }
    }

    private static String swap (String str, int i, int j) {
        char[] s = str.toCharArray();
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
        return String.valueOf(s);
    }

    //Subset (not permutations)
    public static List<List<Integer>> subsets (int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        backtrack(res, new ArrayList<>(), arr, 0);
        return res;
    }

    private static void backtrack (List<List<Integer>> res, List<Integer> ans, int[] arr, int start) {
        res.add (new ArrayList<>(ans));
        for (int i=start; i<arr.length; i++) {
            ans.add(arr[i]);
            backtrack(res, ans, arr, i+1);
            ans.remove(ans.size()-1);
        }
    }

    public static void main(String[] args) {
        permute("abc", 0, 2);
        int[] arr = {1, 2, 3};
        for (List<Integer> res : subsets(arr)) {
            System.out.println(res.toString());
        }
    }
}
