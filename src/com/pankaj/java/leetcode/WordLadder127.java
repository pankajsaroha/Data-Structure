package com.pankaj.java.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder127 {

    public int ladderLength (String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        int len = 1;
        int strLen = beginSet.size();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i=0; i<chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;

                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }
                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }
        return 0;
    }

    /*private boolean oneLetterChange (String s1, String s2) {
        int count = 0;
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
            if (count > 1) return false;
        }
        return count == 1;
    }*/

    public static void main(String[] args) {
        WordLadder127 wl = new WordLadder127();
        List<String> words = List.of("hot","dot","dog","lot","log","cog");
        System.out.println(wl.ladderLength("hit", "cog", words));
    }
}
