package com.pankaj.java.dp;

import java.util.HashMap;
import java.util.Map;

public class CountDecoding2 {

    /*
    * https://leetcode.com/problems/decode-ways-ii/
    * https://leetcode.com/problems/decode-ways-ii/discuss/1365958/DP-O(n)-time.-O(1)-space
    */

    static Map<String, Integer>  map = new HashMap() {
        {
            put("**", 15); //11 to 19 and 21 to 26
            put("*0", 2); // 1 and 2
            put("*1", 2);
            put("*2", 2);
            put("*3", 2);
            put("*4", 2);
            put("*5", 2);
            put("*6", 2);
            put("*7", 1);  //1
            put("*8", 1);
            put("*9", 1);
            put("1*", 9); //1 to 9
            put("2*", 6); //1 to 6
        }
    };

    public static int countDecoding (String str) {
        int n = str.length();
        int dp[] = new int[n+1];
        dp[n] = 1;
        for (int i=n-1; i>=0; i--) {
            if (str.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = ((str.charAt(i) == '*') ? 9 : 1) * dp[i+1];
                if (i < n-1) {
                    String s2 = str.substring(i, i+2);
                    if (s2.indexOf("*") > -1) {
                        dp[i] = dp[i] + map.getOrDefault(s2, 0) * dp[i+2];
                    } else if (str.charAt(i) == '1' || (str.charAt(i) == '2' && str.charAt(i+1) < 7)) {
                        dp[i] = dp[i] + dp[i+2];
                    }
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(countDecoding("**"));
    }
}
