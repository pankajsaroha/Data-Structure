package com.pankaj.java.leetcode;

public class NumberOfWays1639 {
    /*
    * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/submissions/
    * */

    static int numWays (String words[], int index, String target, int word_index) {
        if (index >= target.length()) {
            return 1;
        }
        int sum = 0;
        for (String word : words) {
            for (int i=word_index; i<word.length(); i++) {
                if (word.charAt(i) == target.charAt(index)) {
                    sum += numWays(words, index+1, target, i+1);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //String words[] = {"acca", "bbbb", "caca"};
        String words[] = {"abba", "baab"};
        System.out.println(numWays(words, 0, "bab", 0));
    }
}
