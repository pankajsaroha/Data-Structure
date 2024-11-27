package com.pankaj.java.trie;

public class LongestPrefixMatching {

    TrieNode root;

    public void insert (String key) {

        if (root == null) {
            root = new TrieNode();
        }

        int level = 0;
        TrieNode current = root;

        for (level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    private boolean isEmpty(TrieNode current) {
        for (int i=0; i<26; i++) {
            if (current.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    public String getLongestPrefix (String key) {
        StringBuilder sb = new StringBuilder();
        StringBuilder longest = new StringBuilder();
        int length = 0;
        int level;
        TrieNode current = root;

        for (level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (current.children[index] == null) {
                return sb.toString();
            }
            if (current.children[index] != null) {
                sb.append(key.charAt(level));
                length++;
            }
            if(current.isEndOfWord) {
                longest.append(sb.toString());
                if (isEmpty(current)) break;
            }
            current = current.children[index];
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestPrefixMatching trie = new LongestPrefixMatching();
        String dictionary[] = {"are", "area", "base", "cat", "cater", "basement"};

        for (String str : dictionary) trie.insert(str);

        System.out.println("caterer: " + trie.getLongestPrefix("caterer"));
        System.out.println("basement: " + trie.getLongestPrefix("basement"));
        System.out.println("are: " + trie.getLongestPrefix("are"));
        System.out.println("arex: " + trie.getLongestPrefix("arex"));
        System.out.println("basemexz: " + trie.getLongestPrefix("basemexz"));
        System.out.println("xyz: " + trie.getLongestPrefix("xyz"));
    }
}
