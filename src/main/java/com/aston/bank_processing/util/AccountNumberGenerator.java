package com.aston.bank_processing.util;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class AccountNumberGenerator {
    private final Random random;

    public AccountNumberGenerator(Random random) {
        this.random = random;
    }

    public String getAccountNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("40817810");
        IntStream ints = random.ints(13, 0, 9);
        ints.mapToObj(String::valueOf).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
