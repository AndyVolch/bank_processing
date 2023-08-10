package com.aston.bank_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BankProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankProcessingApplication.class, args);
    }
    @Bean
    public Random getRandom() {
        return new Random();
    }
}
