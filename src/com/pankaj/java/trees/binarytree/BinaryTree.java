package com.pankaj.java.trees.binarytree;

import com.pankaj.java.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
* This class has the implementation of following methods -
* 1. Find Max node in left subtree or right subtree
* 2. Find Min node
* 3. Convert Binary Tree to Binary Search Tree
*/

public class BinaryTree {

    public static Node root;

    public void insert (int data) {
        Node temp = root;
        if(root == null) {
            root = new Node(data);
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(temp);
            while(!queue.isEmpty()) {
                temp = queue.peek();
                queue.remove();
                if(temp.left == null) {
                    temp.left = new Node(data);
                    break;
                }
                queue.add(temp.left);
                if(temp.right == null) {
                    temp.right = new Node(data);
                    break;
                }
                queue.add(temp.right);
            }
        }
    }

    //Inefficient Solution
    public Node maxNode (Node temp, Node max) {
        if(temp == null) {
            return max;
        }
        if(temp.left != null) {
            if(max.data < temp.left.data) {
                max = temp.left;
            }
            maxNode(temp.left, max);
        }
        if(temp.right != null) {
            if(max.data < temp.right.data) {
                max = temp.right;
            }
            maxNode(temp.right, max);
        }
        return max;
    }

    //Efficient Solution
    public int findMax (Node temp) {
        if(temp == null) {
            return Integer.MIN_VALUE;
        }

        int res = temp.data;
        int leftRes = findMax(temp.left);
        int rightRes = findMax(temp.right);

        return Math.max(res, Math.max(leftRes, rightRes));
    }

    static int index;

    /**
     * store the inorder traversal of tree in array
     * @param root
     * @param inorder
     */
    public void storeInOrder(Node root, int inorder[]) {
        if(root == null) {
            return;
        }

        storeInOrder(root.left, inorder);
        inorder[index++] = root.data;
        storeInOrder(root.right, inorder);
    }

    static int start;

    public void arrayToBST(Node root, int inorder[]) {
        if(root == null) {
            return;
        }

        arrayToBST(root.left, inorder);
        root.data = inorder[start++];
        arrayToBST(root.right, inorder);
    }

    public static void inOrder(Node temp) {
        if(temp != null) {
            inOrder(temp.left);
            System.out.print(temp.data + " ");
            inOrder(temp.right);
        }
    }

    public Node mirror (Node node) {
        if (node == null) {
            return node;
        }
        Node left = mirror(node.left);
        Node right = mirror(node.right);
        node.right = left;
        node.left = right;
        return node;
    }

    // root has been considered at height 0.
    public int height (Node node) {
        if (node == null) {
            return -1;
        }
        return 1 + (Math.max(height(node.left), height(node.right)));
    }

    public boolean isBalanced (Node node) {
        if (node == null) {
            return false;
        }
        return balancedHeight(node.left) - balancedHeight(node.right) == -1 ? false : true;
    }

    private int balancedHeight (Node node) {
        if (node == null) {
            return -1;
        }
        return Math.abs(height(node.left) - height(node.right));
    }

    public void topView (Node node) {
        if (node == null) {
            return;
        }
        left(node.left);
        System.out.print(node.data + " ");
        right(node.right);
    }

    void left (Node node) {
        if (node == null) {
            return;
        }
        left(node.left);
        if (node != null) {
            System.out.print(node.data + " ");
        }
    }

    void right (Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        right(node.right);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int arr[] = {8, 10, 18, 20, 15, 26, 30};
        for (int i : arr) {
            bt.insert(i);
        }
        BinaryTree.inOrder(root);
        System.out.println("\nMax node: " + bt.findMax(root));
        bt.storeInOrder(root, arr);
        Arrays.sort(arr);
        System.out.println("Store inorder: " + Arrays.toString(arr));
        System.out.println("Converted BST: ");
        bt.arrayToBST(root, arr);
        BinaryTree.inOrder(root);
        System.out.println("\nHeight: " + bt.height(root));
        System.out.println(bt.isBalanced(root));
        root = new Node(1);
        root.left = null;
        root.right = new Node(2);
        System.out.println("Top View: ");
        bt.topView(root);
    }
}
