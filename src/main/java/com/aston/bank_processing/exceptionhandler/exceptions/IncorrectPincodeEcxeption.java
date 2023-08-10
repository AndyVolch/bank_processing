package com.aston.bank_processing.exceptionhandler.exceptions;

public class IncorrectPincodeEcxeption extends RuntimeException{
    public IncorrectPincodeEcxeption() {
    }

    public IncorrectPincodeEcxeption(String message) {
        super(message);
    }
}

