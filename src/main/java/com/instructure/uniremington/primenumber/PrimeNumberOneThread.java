package com.instructure.uniremington.primenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PrimeNumberOneThread implements Function<Integer, List<Long>> {

    private static final Predicate<Long> IS_PRIME = new IsPrime();

    @Override
    public List<Long> apply(Integer quantity) {

        List<Long> primes = new ArrayList<>(quantity);

        primes.add(2l);

        long number = 3;

        while (primes.size() < quantity) {

            if (IS_PRIME.test(number)) {
                primes.add(number);
            }

            number = number + 2;

        }

        return primes;

    }

}
