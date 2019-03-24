package com.instructure.uniremington.primenumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;

public class PrimeNumberMultipleThreads implements Function<Integer, List<Long>> {

    private static final Predicate<Long> IS_PRIME = new IsPrime();
    private static final int MAX_NUMBER_THREADS = Runtime.getRuntime().availableProcessors();

    @Override
    public List<Long> apply(Integer quantity) {

        // create the thread pool

        ExecutorService pool = Executors.newFixedThreadPool(
            MAX_NUMBER_THREADS);

        // create the empty list that hold the firts quantity primes

        List<Long> primes = new ArrayList<>(quantity);

        // add the first prime

        primes.add(2l);

        // create the begin from iterate the list

        long number = 3;

        // declare the sync object

        CountDownLatch latch;

        // declare block size

        int block;

        while (primes.size() < quantity) {

            // calculate the block size

            block = Math.min(MAX_NUMBER_THREADS, quantity - primes.size());

            // create the sync object

            latch = new CountDownLatch(block);

            // send a block of number of cores of the machine to process

            for (int i = 0; i < block; i++) {
                pool.execute(
                    new TestPrimeAndAddToList(number, primes, IS_PRIME, latch));
                number = number + 2;
            }

            // wait to the process to finish

            try {
                latch.await();
            } catch (InterruptedException e) {
            }

        }

        // sort the primes, remember the list are un-ordered

        Collections.sort(primes);

        // shutdown the thread pool

        pool.shutdown();

        // return the list

        return primes;

    }

    static class TestPrimeAndAddToList implements Runnable {

        private final long number;
        private final List<Long> result;
        private final Predicate<Long> predicate;
        private final CountDownLatch latch;

        public TestPrimeAndAddToList(long number, List<Long> result,
            Predicate<Long> predicate, CountDownLatch latch) {
            this.number = number;
            this.result = result;
            this.predicate = predicate;
            this.latch = latch;
        }

        @Override
        public void run() {

            if (predicate.test(number)) {
                synchronized (result) {
                    result.add(number);
                }
            }

            latch.countDown();

        }

    }

}
