package com.pankaj.java.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BatchCheckPrime {

    AtomicInteger count = new AtomicInteger(0);;
    int MAX_INT = 100000000;

    public boolean checkPrime (int x) {
        if (x <= 1) {
            return false;
        }
        if (x == 2) {
            count.incrementAndGet();
            return true;
        }
        if ((x & 1) == 0) {
            return false;
        }
        for (int i=3; i<=Math.sqrt(x); i+=2) {
            if (x % i == 0) {
                return false;
            }
        }
        count.incrementAndGet();
        return true;
    }

    public void createBatchAndProcess (int start, int end) {
        int count = 0; //local count in this range
        long st = System.currentTimeMillis();
        for (int i = start + 1; i < end; i++) {
            if(checkPrime(i)) count++;
        }
        System.out.printf("%s: There are %d prime numbers from %d to %d and took time %f\n", Thread.currentThread().getName(), count, start, end, ((float)(System.currentTimeMillis() - st) / 1000));
    }

    public static void main(String[] args) {
        BatchCheckPrime bc = new BatchCheckPrime();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int batchSize = bc.MAX_INT / 4;
        long start = System.
                currentTimeMillis();
        for (int i = 0; i < bc.MAX_INT; i = i + batchSize) {
            int finalBatchStart = i;
            executorService.execute(() -> bc.createBatchAndProcess(finalBatchStart, finalBatchStart + batchSize));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("There are %d prime numbers from 1 to %d and took time %f", bc.count.get(), bc.MAX_INT, ((float)(System.currentTimeMillis() - start) / 1000));
    }
}
