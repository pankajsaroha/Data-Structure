package com.pankaj.java;

public class UberTest2 {

    int solution(int n, int[] cabTripTime) {
        int low = 0, high = Integer.MAX_VALUE;
        int res = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int trips = 0;
            for (int trip : cabTripTime) {
                trips += (mid / trip);
            }
            if (trips >= n) {
                res = mid;
                high = mid - 1;
            } else if (trips < n){
                low = mid + 1;
            }
        }
        return res;
    }


    public static void main(String args[]) {
        UberTest2 t = new UberTest2();
        //int[] arr = {1, 3, 5, 7};
        int[] arr = {23, 28, 8, 20, 28, 27, 18};
        System.out.println(t.solution(19, arr));
    }
}