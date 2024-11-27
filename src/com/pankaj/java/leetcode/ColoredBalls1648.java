package com.pankaj.java.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ColoredBalls1648 {

    //Sum of n consecutive numbers = (n/2) (first number + last number)
    //https://leetcode.com/problems/sell-diminishing-valued-colored-balls/discuss/927720/Java-PriorityQueue-%2B-Math

    public int maxProfit(int[] inventory, int orders) {
        int mod = 1000000007;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int num : inventory) {
            pq.add(num);
        }

        int cur = pq.poll();
        int count = 1;
        long res = 0;

        while (orders > 0) {
            int next = pq.isEmpty() ? 0 : pq.peek();
            if ((cur - next) * count <= orders) {
                long sum = (1L * (next + 1 + cur) * (cur - next) * count / 2) % mod;
                res = (res + sum) % mod;
                orders = orders - (cur - next) * count;
            } else {
                next = cur - orders / count;
                long sum = (1L * (next + 1 + cur) * (cur - next) * count / 2) % mod;
                res = (res + sum) % mod;

                res = (res + 1L * next * (orders % count)) % mod;
                orders = 0;
            }

            if (!pq.isEmpty()) {
                cur = pq.poll();
            }
            count++;
        }

        return (int) res;
    }

    public static void main(String[] args) {
        int arr[] = {2, 5};
        System.out.println(1L);
        System.out.println(new ColoredBalls1648().maxProfit(arr, 4));
    }
}
