package com.pankaj.java;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {

    //there is one more implementation using trie under com.pankaj.trie package
    static boolean workBreak (String str, Set<String> dict) {
        boolean[] dp = new boolean[str.length()+1];
        dp[0] = true;

        for (int i=0; i<str.length(); i++) {
            if (!dp[i]) {
                continue;
            }
            for (String a : dict) {
                int len = a.length();
                int end = i+len;

                if (end > str.length()) {
                    continue;
                }

                if (str.substring(i, end).equals(a)) {
                    dp[end] = true;
                }
            }
        }
        return dp[str.length()];
    }

    public static void main(String args[]) {
        String str = "programcreek";
        Set<String> dict = new HashSet<>();
        dict.add("programcree");
        dict.add("program");
        dict.add("creek");
        System.out.println(workBreak(str, dict));
    }
}
