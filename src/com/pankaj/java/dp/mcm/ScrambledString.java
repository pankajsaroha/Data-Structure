package com.pankaj.java.dp.mcm;

import java.util.HashMap;
import java.util.Map;

public class ScrambledString {

    static Map<String, Boolean> map;

    public boolean scramble (String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }

        int n = str1.length();

        if (n == 0 || str1.equals(str2)) {
            return true;
        }

        boolean flag = false;
        String key = str1 + " " + str2;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        for (int i=1; i<n; i++) {
            if (scramble(str1.substring(0, i), str2.substring(n-i, n)) &&
            scramble(str1.substring(i, n), str2.substring(0, n-i))) {
                flag = true;
                break;
            }

            if (scramble(str1.substring(0, i), str2.substring(0, i)) &&
            scramble(str1.substring(i, n), str2.substring(i, n))) {
                flag = true;
                break;
            }
        }
        map.put(key, flag);
        return flag;
    }

    public static void main(String[] args) {
        ScrambledString s = new ScrambledString();
        String str1 = "pankaj";
        String str2 = "ocdere";
        map = new HashMap<>();
        System.out.println(s.scramble(str1, str2));
    }
}
