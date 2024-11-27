package com.pankaj.java.google;

import java.util.*;

/*
* https://leetcode.com/problems/word-break-ii/
* */
public class WordBreak2 {

    public List<String> wordBreak (String s, Set<String> wordDict) {
        return DFS (s, wordDict, new HashMap<>());
    }

    private List<String> DFS (String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subList = DFS (s.substring(word.length()), wordDict, map);
                for (String sub : subList) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put (s, res);
        return res;
    }

    private List<String> DFS3 (String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (int i=1; i<s.length()+1; i++) {
            String t = s.substring(0, i);
            if (wordDict.contains(t)) {
                List<String> subList = DFS3(s.substring(i), wordDict, map);
                for (String sub : subList) {
                    res.add(t + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    //second solution
    public List<String> wordBreak2 (String s, Set<String> wordDict) {
        return DFS3(s, wordDict, new HashMap<>());
    }

    private List<String> DFS2 (String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (wordDict.contains(s)) {
            res.add(s);
        }
        for (int i=1; i<s.length(); i++) {
            String t = s.substring(i);
            if (wordDict.contains(t)) {
                List<String> subList = DFS2 (s.substring(0, i), wordDict, map);
                if (subList.size() != 0) {
                    for (int j=0; j<subList.size(); j++) {
                        res.add(subList.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreak2 wb = new WordBreak2();
        String s = "catsanddog";
        String[] wordDict = {"cat","cats","and","sand","dog"};
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        System.out.println(wb.wordBreak2(s, set).toString());
    }
}
