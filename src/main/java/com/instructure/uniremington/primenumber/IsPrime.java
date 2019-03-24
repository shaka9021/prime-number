package com.instructure.uniremington.primenumber;

import java.util.function.Predicate;

public class IsPrime implements Predicate<Long> {

    @Override
    public boolean test(Long number) {

        // WARINIG:
        // simulate hard work!

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {}

        // test if number is a prime number

        if (number != 2 && number % 2 == 0) {
            return false;
        }

        long sqrt = (long) Math.sqrt(number);

        for (int i = 3; i <= sqrt; i = i + 2) {

            if (number % i == 0) {
                return false;
            }

        }

        return true;

    }

}
