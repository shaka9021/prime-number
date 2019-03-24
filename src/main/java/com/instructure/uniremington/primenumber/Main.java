package com.instructure.uniremington.primenumber;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // entropy

        new PrimeNumberOneThread().apply(100);
        new PrimeNumberMultipleThreads().apply(100);

        // how many primes?

        int quantity = 500;

        // without threads

        PrimeNumberOneThread single = new PrimeNumberOneThread();

        long before = System.currentTimeMillis();
        List<Long> primes = single.apply(quantity);
        long after = System.currentTimeMillis();
        long without = after - before;

        System.out.printf("Time without threads: %s ms Prime List: %s \r\n",
            without, primes);

        // without threads

        PrimeNumberMultipleThreads multiple = new PrimeNumberMultipleThreads();

        before = System.currentTimeMillis();
        primes = multiple.apply(quantity);
        after = System.currentTimeMillis();
        long with = after - before;

        System.out.printf("Time with %s threads: %s ms Prime List: %s \r\n",
            Runtime.getRuntime().availableProcessors(),
            with, primes);

        System.out.printf("Diference with threads and without threads: %s ms \r\n", without - with);
    }

}
