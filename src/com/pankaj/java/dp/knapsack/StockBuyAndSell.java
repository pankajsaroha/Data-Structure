package com.pankaj.java.dp.knapsack;

import java.util.Arrays;

public class StockBuyAndSell {

    public int buySell (int arr[], int start, int end) {
        if (start >= end) {
            return 0;
        }
        int profit = 0;

        for (int i=start; i<end; i++) {
            for (int j=i+1; j<=end; j++) {
                if (arr[j] > arr[i]) {
                    int first = buySell(arr, start, i-1);
                    int second = buySell(arr, j+1, end);
                    int current_profit = arr[j] - arr[i]
                            + first
                            + second;
                    profit = Math.max(profit, current_profit);
                }
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        StockBuyAndSell stck = new StockBuyAndSell();
        int arr[] = {100, 180, 260, 310, 40, 535, 695};
        System.out.println(stck.buySell(arr, 0, arr.length-1));
    }
}
