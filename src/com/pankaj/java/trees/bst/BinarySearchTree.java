package com.pankaj.java.trees.bst;

import com.pankaj.java.Node;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Queue;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

class BinarySearchTree {

    Node root;

    public void insertItr(int data) {
        if(root == null) {
            root = new Node(data);
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.peek();
            queue.remove();
            if(data < node.data) {
                if(node.left == null) {
                    node.left = new Node(data);
                    return;
                }
                queue.add(node.left);
            } else {
                if(node.right == null) {
                    node.right = new Node(data);
                    return;
                }
                queue.add(node.right);
            }
        }
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    public Node insertRec(Node temp, int data) {
        if(temp == null) {
            return new Node(data);
        } else {
            if(data < temp.data) {
                temp.left = insertRec(temp.left, data);
            } else {
                temp.right = insertRec(temp.right, data);
            }
        }
        return temp;
    }

    public void insertLoop(int data) {
        if(root == null) {
            root = new Node(data);
            return;
        }
        Node temp = root;
        Node prev = null;
        while(temp != null) {
            prev = temp;
            if(data < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if(data < prev.data) {
            prev.left = new Node(data);
        } else {
            prev.right = new Node(data);
        }
    }

    public void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node temp, int data) {
        if(temp == null) {
            return temp;
        }
        if(data < temp.data) {
            temp.left = deleteRec(temp.left, data);
        } else if (data > temp.data) {
            temp.right = deleteRec(temp.right, data);
        } else {
            if(temp.left == null) {
                return temp.right;
            }
            if(temp.right == null) {
                return temp.left;
            }

            temp.data = inorderSuccessor(temp.right);
            temp.right = deleteRec(temp.right, temp.data);
        }
        return temp;
    }

    //This code (deleteOptimised) was not accepted in leetcode. deleteRec is working.
    Node deleteOptimised (Node temp, int data) {
        if(temp == null) return temp;

        if (data < temp.data) {
            temp.left = deleteOptimised(temp.left, data);
        } else if (data > temp.data) {
            temp.right = deleteOptimised(temp.right, data);
        } else {
            if(temp.left == null) {
                return temp.right;
            }
            if (temp.right == null) {
                return temp.left;
            }
            Node succParent = temp; Node succ = temp.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            if(succParent != temp) {
                succParent.left = null;
            } else {
                temp.right = succ.right;
            }
            temp.data = succ.data;
            return temp;
        }
        return temp;
    }

    int inorderSuccessor(Node temp) {
        while(temp.left != null) {
            temp = temp.left;
        }
        return temp.data;
    }

    public void inorder(Node temp) {
        if(temp != null) {
            inorder(temp.left);
            System.out.print(temp.data + " ");
            inorder(temp.right);
        }
    }

    public void inorderLoop (Node temp) {
        if(temp == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node current = temp;
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    public void inorderLoopWithoutStack (Node root) {
        if (root == null) {
            return;
        }
        Node current = root; Node pre;

        while (current != null) {

            if (current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                pre = current.left;

                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }

                if(pre.right == null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null;
                    System.out.print (current.data + " ");
                    current = current.right;
                }
            }
        }
    }

    int preindex = 0; // To be used for postWithoutHashing method

    private void postWithoutHashing(int in[], int start, int end, int pre[]) {
        if(start > end) return;

        int root = search(in, pre[preindex++]);
        postWithoutHashing(in, start, root-1, pre);
        postWithoutHashing(in, root+1, end, pre);

        System.out.print(in[root] + " ");
    }

    //Complexity - O(n^2)
    public void postFromPreInWithoutHashing(int inorder[], int pre[]) {
        postWithoutHashing(inorder, 0, pre.length-1, pre);
    }

    private int search(int inorder[], int n) {
        for(int i=0; i<inorder.length; i++) {
            if(inorder[i] == n) return i;
        }
        return -1;
    }

    //Complexity - O(n)
    public void postFromPreInWithHashing(int inorder[], int pre[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        postWithHashing(inorder, pre, map, 0, pre.length - 1);
    }

    int preindexHashing = 0;
    private void postWithHashing(int inorder[], int pre[], Map map, int start, int end) {
        if (start > end) {
            return;
        }

        int root = (int) map.get(pre[preindexHashing++]);

        postWithHashing(inorder, pre, map, start, root-1);
        postWithHashing(inorder, pre, map, root+1, end);

        System.out.print(inorder[root] + " ");
    }



    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        //int arr[] = {5, 3, 4, 2, 7, 6, 8, 9, 1};
       /* int arr[] = {10, 6, 12, 8, 7, 9};
        for(int n : arr) {
            bst.insertLoop(n);
        }
        System.out.println("Tree in order: ");
        bst.inorder(bst.root);
        System.out.println();
        bst.delete(6);
        System.out.println("After deletion: ");
        bst.inorderLoopWithoutStack(bst.root);

        System.out.println("\nPost Order traversal from Pre and Inorder: ");
        int in[] = { 4, 2, 5, 1, 3, 6 };
        int pre[] = { 1, 2, 4, 5, 3, 6 };
        bst.postFromPreInWithoutHashing(in, pre);
        bst.postFromPreInWithHashing(in, pre);*/

        int arr[] = {5, 3, 8, 4, 2, 1, 7, 6, 9};
        for (int val : arr) {
            bst.insert (val);
        }
        bst.root.left.right.right = new Node (11);
        bst.inorder(bst.root);
        System.out.println ();
        bst.root = bst.deleteRec(bst.root, 11);
        bst.delete(5);
        bst.inorder(bst.root);
    }
}