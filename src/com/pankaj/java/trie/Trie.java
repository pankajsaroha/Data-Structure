package com.pankaj.java.trie;

public class Trie {

    TrieNode root;

    public void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode current = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    public boolean search(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode current = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }

        return current.isEndOfWord;
    }

    public TrieNode delete (TrieNode root, String key, int depth) {
        if (root == null) {
            return null;
        }

        if (depth == key.length()) {
            if (root.isEndOfWord) {
                root.isEndOfWord = false;
            }

            if (isEmpty(root)) {
                root = null;
            }
            return root;
        }

        int index = key.charAt(depth) - 'a';
        root.children[index] = delete(root.children[index], key, depth + 1);

        if(isEmpty(root) && root.isEndOfWord == false) {
            root = null;
        }

        return root;
    }

    private boolean isEmpty (TrieNode root) {
        for (int i=0; i<26; i++) {
            if (root.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        trie.root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);

        // Search for different keys
        if(trie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(trie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(trie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(trie.search("answer") == true)
            System.out.println("answer --- " + output[1]);
        else System.out.println("answer --- " + output[0]);

        trie.delete(trie.root, "answer", 0);

        System.out.println(trie.search("answer"));
    }
}
