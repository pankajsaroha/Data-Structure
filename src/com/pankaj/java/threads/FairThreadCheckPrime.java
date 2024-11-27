package com.pankaj.java.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FairThreadCheckPrime {

    AtomicInteger count = new AtomicInteger(0);
    int MAX_INT = 100000000;
    AtomicInteger currentNumber = new AtomicInteger(0);

    public void checkPrime (int x) {
        if (x <= 1) {
            return;
        }
        if (x == 2) {
            count.incrementAndGet();
            return;
        }
        if ((x & 1) == 0) {
            return;
        }
        for (int i=3; i<=Math.sqrt(x); i+=2) {
            if (x % i == 0) {
                return;
            }
        }
        count.incrementAndGet();
        return;
    }

    public void checkRoundRobin () {
        while (currentNumber.get() < MAX_INT) {
            checkPrime(currentNumber.getAndIncrement());
        }
    }

    public static void main(String[] args) {
        FairThreadCheckPrime ft = new FairThreadCheckPrime();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                long st = System.nanoTime();
                ft.checkRoundRobin();
                System.out.printf("%s: Thread took time : %f \n", Thread.currentThread().getName(), ((float) System.nanoTime() - st) / 1000000000);
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("There are %d prime numbers from 1 to %d and took time %f", ft.count.get(), ft.MAX_INT, ((float)(System.currentTimeMillis() - start) / 1000));
    }
}
