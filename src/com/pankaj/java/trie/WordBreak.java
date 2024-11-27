package com.pankaj.java.trie;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    static class TrieNode {
        char c;
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode (char c) {
            this.c = c;
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }

    TrieNode root;

    public TrieNode insert (TrieNode root, String word) {
        if (root == null) {
            root = new TrieNode('\0');
        }
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(c);
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
        return root;
    }

    public boolean checkWord (TrieNode root, String s, int start, int[] dp) {
        if (s.length() == start) {
            return true;
        }
        TrieNode current = root;
        for (int i=start; i<s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (current.children[index] == null) {
                dp[start] = 0;
                return false;
            }
            current = current.children[index];
            if (current.isEndOfWord && checkWord(root, s, i + 1, dp)) {
                dp[start] = 1;
                return true;
            }
        }
        dp[start] = 0;
        return false;
    }

    public boolean wordBreak (String s, List<String> wordDict) {
        for (String word : wordDict) {
            root = insert(root, word);
        }
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return checkWord(root, s, 0, dp);
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
    }
}
