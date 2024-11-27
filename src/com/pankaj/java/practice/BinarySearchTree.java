package com.pankaj.java.practice;

import java.util.*;

public class BinarySearchTree {

    Node root;

    class Node {
        int data;
        Node left;
        Node right;

        //empty Node
        Node () {}
        Node (int data) {
            this.data = data;
        }
    }

    public Node insert (Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (root.data > data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public void inorder (Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void preorder (Node root) {
        if (root != null) {
            System.out.print(root.data + " ");;
            preorder(root.left);
            preorder(root.right);
        }
    }

    public List<Node> midElements (Node root) {
        List<Node> res = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            for (int i=0; i<Math.pow(2, level); i++) {
                Node node = Optional.ofNullable(queue.poll()).orElse(new Node());
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            level++;

            Node first = queue.pollFirst();
            Node last = queue.pollLast();

            res.addAll(queue);
            if (first != null) queue.addFirst(first);
            if (last != null) queue.addLast(last);

            Iterator<Node> itr = res.iterator();
            while (itr.hasNext()) {
                Node node = itr.next();
                if (leafNode(node)) {
                    itr.remove();
                }
            }
        }
        return res;
    }

    private boolean leafNode (Node node) {
        return node.left == null && node.right == null;
    }

    public int height (Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced (Node root) {
        if (root == null)
            return true;

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int isBalancedOptimized (Node root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedOptimized(root.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalancedOptimized(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }

    }

    public void printMidElements (Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        System.out.println(root.data + " ");

        while (!queue.isEmpty()) {
            for (int i=0; i<Math.pow(2, level); i++) {
                Node node = Optional.ofNullable(queue.poll()).orElse(new Node());
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            level++;
            queue.stream().map(node -> node.data + " ").forEach(System.out::print);
            System.out.println();
        }
    }

    public boolean identicalTrees (Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        return root1 != null && root2 != null ? root1.data == root2.data &&
                identicalTrees(root1.left, root2.left) && identicalTrees(root1.right, root2.right) : false;
    }

    public Node mirrorTree (Node root) {
        if (root == null) {
            return root;
        }
        Node left = mirrorTree(root.left);
        Node right = mirrorTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    public boolean isBST (Node root) {
        return validateBST(root, null, null);
    }

    private boolean validateBST (Node root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.data <= min) || (max != null && root.data > max)) {
            return false;
        }
        return validateBST(root.left, min, root.data) && validateBST(root.right, root.data, max);
    }

    public Node sortedArrToBST (int[] arr, int start, int end) {
        if (arr == null || start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = sortedArrToBST(arr, start, mid-1);
        root.right = sortedArrToBST(arr, mid+1, end);
        return root;
    }

    public List<Integer> largestValueEachLevel (Node root) {
        List<Integer> res = new ArrayList<>();
        return largestValueEachLevelHelper(root, res, 0);
    }

    private List<Integer> largestValueEachLevelHelper (Node root, List<Integer> res, int level) {
        if (root == null) {
            return res;
        }
        if (level == res.size()) {
            res.add(root.data);
        } else {
            res.set(level, Math.max(res.get(level), root.data));
        }
        largestValueEachLevelHelper(root.left, res, level+1);
        largestValueEachLevelHelper(root.right, res, level+1);
        return res;
    }

    public List<Integer> largestValueEachLevelQueue (Node root) {
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            for (int i=0; i< Math.pow(2, level); i++) {
                Node node = queue.poll();
                if (node != null) {
                    if (result.size() == level) {
                        result.add(node.data);
                    } else {
                        result.set(level, Math.max(result.get(level), node.data));
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            level++;
        }
        return result;
    }

    public void zigzagTraversal (Node root) {
        if (root == null) {
            return;
        }
        boolean leftToRight = true;
        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();

        currentLevel.push(root);
        while (!currentLevel.isEmpty()) {
            Node node = currentLevel.pop();
            System.out.print(node.data + " ");

            if (leftToRight) {
                if (node.left != null)
                    nextLevel.push(node.left);
                if (node.right != null)
                    nextLevel.push(node.right);
            } else {
                if (node.right != null)
                    nextLevel.push(node.right);
                if (node.left != null)
                    nextLevel.push(node.left);
            }

            if (currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }

    public List<Integer> distanceK (Node root, int target, int k) {
        List<Node> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        nodeToRootPath(root, list, target);
        for (int i=0; i<list.size(); i++) {
            if (i == 0) {
                kLevelDown(list.get(i), k-i, 0, null, result);
            } else {
                kLevelDown(list.get(i), k-i, 0, list.get(i-1), result);
            }
        }
        return result;
    }

    private boolean nodeToRootPath (Node root, List<Node> list, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            list.add(root);
            return true;
        }
        boolean left = nodeToRootPath(root.left, list, target);
        if (left) {
            list.add(root);
            return true;
        }
        boolean right = nodeToRootPath(root.right, list, target);
        if (right) {
            list.add(root);
            return true;
        }
        return false;
    }

    private void kLevelDown (Node node, int k, int dist, Node blocker, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (blocker == node) {
            return;
        }
        if (dist == k) {
            result.add(node.data);
            return;
        }
        kLevelDown(node.left, k, dist+1, blocker, result);
        kLevelDown(node.right, k, dist+1, blocker, result);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {8, 4, 15, 2, 5, 12, 18, 1, 3, 9, 13};

        Node root2 = null;
        for (int data : arr) {
            bst.root = bst.insert(bst.root, data);
            root2 = bst.insert(root2, data);
        }
        //root2.right.left.left = null;
        //bst.inorder(bst.root);
        /*List<Node> res = bst.midElements(bst.root);
        for (Node node : res) {
            System.out.print(node.data + " ");
        }*/
        //bst.inorder(bst.root);
        //System.out.println(bst.isBalanced(bst.root));
        //bst.root = bst.sortedArrToBST(arr, 0, arr.length-1);
        //bst.preorder(bst.root);
        //System.out.println(bst.largestValueEachLevelQueue(bst.root).toString());
        //bst.zigzagTraversal(bst.root);
        System.out.println(bst.distanceK(bst.root, 4, 2));
    }
}
