package com.pankaj.java.leetcode;

import java.util.Arrays;

public class MinCostForTickets983 {

    public static int minCostTickets (int[] days, int[] cost) {
        int[] dp = new int[366];
        dp[0] = 0;
        int day = 0;
        for (int i=1; i<366; i++) {
            if (day < days.length && i == days[day]) {
                int oneDayPass = dp[Math.max(0, i - 1)] + cost[0];
                int sevenDayPass = dp[Math.max(0, i - 7)] + cost[1];
                int thirtyDayPass = dp[Math.max(0, i - 30)] + cost[2];

                dp[i] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
                day++;
            } else {
                dp[i] = dp[i-1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[365];
    }

    public static void main(String[] args) {
        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = {2, 7, 15};

        System.out.println(minCostTickets(days, costs));
    }
}
