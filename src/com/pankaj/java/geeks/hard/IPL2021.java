package com.pankaj.java.geeks.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class IPL2021 {

    /*
    * Question -
    * IPL 2021 Finals are here and it is between the most successful team of the IPL Mumbai Indians and the team
    * striving to garb their first trophy Royal Challengers Banglore. Rohit Sharma, captain of the team Mumbai Indians
    * has the most experience in IPL finals, he feels lucky if he solves a programming question before the IPL finals.
    * So, he asked the team's head coach  Mahela Jayawardene for a question. Question is, given a string S consisting
    * only of opening and closing parenthesis 'ie '('  and ')',  the task is to find out the length of the longest
    * valid parentheses substring.
    * NOTE: The length of the smallest valid substring ( ) is 2.
    */

    static int findMaxLen (String str) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(-1);
        int len = 0;

        for (int i=0; i<str.length(); i++) {
            if (stack1.isEmpty() && str.charAt(i) == ')') {
                stack2.push(i);
            } else if (str.charAt(i) == '(') {
                stack1.push('(');
                stack2.push(i);
            } else if (!stack1.isEmpty() && str.charAt(i) == ')') {
                stack1.pop();
                stack2.pop();
                len = Math.max(len, i - stack2.peek());
            }
        }
        return len;
    }

    //Geeks Solution
    static int findMaxLenGeeks (String str) {
        int len = 0;
        int open = 0;
        int close = 0;
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open  == close) {
                len = Math.max(len, 2 * close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = close = 0;

        for (int i = str.length() - 1; i > 0; i--) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                len = Math.max(len, 2 * close);
            } else if (open > close) {
                open = close = 0;
            }
        }
        return len;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(findMaxLen(str));
        System.out.println(findMaxLenGeeks(str));
    }
}
