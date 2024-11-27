package com.pankaj.java.trees;

import com.pankaj.java.Node;
import com.pankaj.java.trees.binarytree.BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class TreeFromInPre {

    Node root;
    static int preindex = 0;

    public Node buildTree(int inorder[], int pre[], int start, int end) {
        if(start > end) {
            return null;
        }

        Node temp = new Node(pre[preindex++]);

        if(start == end) {
            return temp;
        }

        int index = search(inorder, temp.data, start, end);

        temp.left = buildTree(inorder, pre, start, index-1);
        temp.right = buildTree(inorder, pre, index+1, end);

        return temp;
    }

    private int search(int inorder[], int node, int start, int end) {
        int i;
        for(i=start; i<end; i++){
            if(inorder[i] == node) {
                return i;
            }
        }
        return i;
    }

    static int preIndexMap;

    public Node buildTreeUsingMap(int in[], int pre[], int start, int end, Map<Integer, Integer> map) {
        if(start > end) {
            return null;
        }

        Node temp = new Node(pre[preIndexMap++]);

        if(start == end) {
            return temp;
        }

        int index = map.get(temp.data);

        temp.left = buildTreeUsingMap(in, pre, start, index-1, map);
        temp.right = buildTreeUsingMap(in, pre, index+1, end, map);

        return temp;
    }

    private Map<Integer, Integer> storeIndexOfInorder(int in[], Map<Integer, Integer> map) {
        for(int i=0; i<in.length; i++) {
            map.put(in[i], i);
        }
        return map;
    }

    public static void main(String[] args) {
        TreeFromInPre tree = new TreeFromInPre();
        int in[] = {4, 2, 5, 1, 6, 3};
        int pre[] = {1, 2, 4, 5, 3, 6};

        Map<Integer, Integer> map = new HashMap<>();
        map = tree.storeIndexOfInorder(in, map);

        //tree.root = tree.buildTree(in, pre, 0, in.length-1);
        tree.root = tree.buildTreeUsingMap(in, pre, 0, in.length-1, map);

        System.out.print("Tree: ");
        BinaryTree.inOrder(tree.root);
    }
}
