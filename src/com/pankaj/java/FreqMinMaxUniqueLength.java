package com.pankaj.java;

import java.util.HashMap;
import java.util.Map;

public class FreqMinMaxUniqueLength {

    public static int countFreq (String s, int minLength, int maxLength, int maxUnique) {
        int n = s.length(), freq = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int len = minLength; len <= maxLength; len++) {
            int[] count = new int[26];
            int unique = 0;

            for (int i=0; i<n; i++) {
                if (i >= len) {
                    int index = s.charAt(i - len) - 'a';
                    count[index]--;
                    if (count[index] == 0) {
                        unique--;
                    }
                }

                int ind = s.charAt(i) - 'a';
                count[ind]++;
                if (count[ind] == 1) {
                    unique++;
                }

                if (i >= len - 1) {
                    if (unique <= maxUnique) {
                        String sub = s.substring(i - len + 1, i + 1);
                        map.put(sub, map.getOrDefault(sub, 0) + 1);
                        freq = Math.max(freq, map.get(sub));
                    }
                }
            }
        }
        System.out.println(map.toString());
        return freq;
    }

    public static void main(String[] args) {
        FreqMinMaxUniqueLength s = new FreqMinMaxUniqueLength();
        System.out.println(countFreq("abcaabc", 2, 3, 3));
    }
}
