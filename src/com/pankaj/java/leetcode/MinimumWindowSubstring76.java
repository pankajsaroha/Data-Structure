package com.pankaj.java.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {

    public String minWindowTwoPointer (String s, String t) {
        int l = 0, r = 0;
        Map<Character, Integer> input = new HashMap<>();
        //window length, left, right
        int[] result = {-1, 0, 0};

        for (int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            int count = input.getOrDefault(c, 0);
            input.put(c, count+1);
        }

        int required = input.size();
        int formed = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count+1);

            if (input.containsKey(c) && windowCounts.get(c) == input.get(c)) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (result[0] == -1 || r - l + 1 < result[0]) {
                    result[0] = r - l + 1;
                    result[1] = l;
                    result[2] = r;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (input.containsKey(c) && windowCounts.get(c) < input.get(c)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    public String minWindowSliding (String s, String t) {
        Map <Character, Integer> input = new HashMap<>();
        for (int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            int count = input.getOrDefault (c, 0);
            input.put (c, count+1);
        }

        ArrayList<Pair<Integer, Character>> filteredString = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            if (input.containsKey(s.charAt(i))) {
                filteredString.add (new Pair<Integer, Character>(i, s.charAt(i)));
            }
        }

        int l = 0, r = 0, formed = 0;
        int required = input.size();
        Map <Character, Integer> window = new HashMap <>();
        int[] result = {-1, 0, 0};

        while (r < filteredString.size()) {
            char c = filteredString.get(r).getValue();
            int count = window.getOrDefault (c, 0);
            window.put (c, count+1);

            if (input.containsKey(c) && input.get(c) == window.get(c)) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = filteredString.get(l).getValue();
                int start = filteredString.get(l).getKey();
                int end = filteredString.get(r).getKey();

                if (result[0] == -1 || r - l + 1 < result[0]) {
                    result[0] = r - l  + 1;
                    result[1] = start;
                    result[2] = end;
                }
                window.put (c, window.get(c) - 1);
                if (input.containsKey(c) && window.get(c) < input.get(c)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return result[0] == -1 ? "" : s.substring (result[1], result[2]+1);
    }

    class Pair<Integer, Character> {
        int i;
        char c;
        public Pair (int i, char c) {
            this.i = i;
            this.c = c;
        }

        public int getKey () {
            return this.i;
        }

        public char getValue () {
            return this.c;
        }
    }

    public static void main(String[] args) {
        MinimumWindowSubstring76 test = new MinimumWindowSubstring76();
        String s = "ABCDDDDDDEEAFFBC", t = "ABC";
        System.out.println(test.minWindowTwoPointer(s, t));
        System.out.println("-------------------------- ");
        System.out.println(test.minWindowSliding(s, t));
    }
}
