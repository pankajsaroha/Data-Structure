package com.pankaj.java.threads;

public class SeqCheckPrime {

    int count;
    int MAX_INT = 100000000;

    public void checkPrime (int x) {
        if (x <= 1) {
            return;
        }
        if (x == 2) {
            count++;
            return;
        }
        if ((x & 1) == 0) {
            return;
        }
        if ((x & 1) == 0) {
            return;
        }
        for (int i=3; i<=Math.sqrt(x); i++) {
            if (x % i == 0) {
                return;
            }
        }
        count++;
    }

    public static void main(String[] args) {
        SeqCheckPrime sq = new SeqCheckPrime();
        long start = System.currentTimeMillis();
        for (int i = 1; i <= sq.MAX_INT; i++) {
            sq.checkPrime(i);
        }
        float f = (System.currentTimeMillis() - start) / 1000;
        System.out.printf("There are %d prime numbers from 1 %d and took time %f", sq.count, sq.MAX_INT, ((float)(System.currentTimeMillis() - start) / 1000));
    }
}
