package com.pankaj.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII212 {

    /*
    * https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
    * */

    class TrieNode {
        TrieNode children[] = new TrieNode[26];
        String word;
    }

    public TrieNode buildTree(String words[]) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) {
                    p.children[i] = new TrieNode();
                }
                p = p.children[i];
            }
            p.word = w;
        }
        return root;
    }

    public void dfs (char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) {
            return;
        }
        p = p.children[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';

        if (i > 0) {
            dfs(board, i-1, j, p, res);
        }
        if (j > 0) {
            dfs(board, i, j-1, p, res);
        }
        if (i < board.length - 1) {
            dfs(board, i+1, j, p, res);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j+1, p, res);
        }
        board[i][j] = c;
    }

    public List<String> findWords (char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTree(words);

        for (int i=0; i < board.length; i++) {
            for (int j=0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char board[][] = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String words[] = {"oath","pea","eat","rain"};

        System.out.println(new WordSearchII212().findWords(board, words).toString());
    }
}
