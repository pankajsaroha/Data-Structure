package com.pankaj.java.dp;

public class CountDecoding {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static long result = 0;
    static long numOfZeroes = 0;

    static int countDecoding(String inputStr, int n)
    {
        if (n == 0 || n == 1)
            return 1;

        if (inputStr.charAt(0) == '0')
            return 0;

        int count = 0;

        if (inputStr.charAt(n - 1) > '0')
            count = countDecoding(inputStr, n - 1);

        if (inputStr.charAt(n - 2) == '1'
                || (inputStr.charAt(n - 2) == '2'
                && inputStr.charAt(n - 1) < '7'))
            count += countDecoding(inputStr, n - 2);

        return count;
    }

    static int countWays(String inputStr, int n)
    {
        if (n == 0 || (n == 1 && inputStr.charAt(0) == '0'))
            return 0;
        return countDecoding(inputStr, n);
    }

    static int countWaysUsingDp(String inputStr) {
        int count[] = new int[inputStr.length()+1];
        count[0] = 0;
        count[1] = 1;
        if (inputStr.charAt(0) == '0') {
            return 0;
        }
        for (int i = 2; i <= inputStr.length(); i++) {
            //count[i] = 0;
            if (inputStr.charAt(i-1) > '0') {
                count[i] = count[i-1];
            }

            if (inputStr.charAt(i-2) == '1' || (inputStr.charAt(i-2) == '2' && inputStr.charAt(i-1) < '7')) {
                count[i] = count[i] + count[i-2];
            }
        }
        return count[inputStr.length()];
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] cache = new int[n+1];
        cache[n] = 1;
        cache[n-1] = s.charAt(n-1) == '0' ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') continue;
            cache[i] = cache[i+1];  // cache[i] += cache[i+1] is also right because cache[i] will be 0.
            if (c == '1' || (c == '2' && (s.charAt(i+1) - '0' < 7))) {
                cache[i] += cache[i+2];
            }
        }
        return cache[0];
    }

    public static void main(String[] args)
    {
        //String digits = "6112";
        String inputStr = "2112";
        //System.out.println(countWays(inputStr, 4));
        System.out.println(numDecodings(inputStr));


    }
}
