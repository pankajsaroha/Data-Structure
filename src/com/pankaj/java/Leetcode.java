package com.pankaj.java;

public class Leetcode {

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int day = 0;
        for (int i=1; i<366; i++) {
            if (day < days.length && i == days[day]) {
                int oneDPass = dp[Math.max(0, i-1)] + costs[0];
                int sevenDPass = dp[Math.max(0, i-7)] + costs[1];
                int thirtyDPass = dp[Math.max(0, i-30)] + costs[2];
                dp[i] = Math.min(oneDPass, Math.min(sevenDPass, thirtyDPass));
                day++;
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[365];
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode().mincostTickets(new int[]{1, 4, 6, 7, 8, 20},
                new int[]{2, 7, 15}));
    }
}
