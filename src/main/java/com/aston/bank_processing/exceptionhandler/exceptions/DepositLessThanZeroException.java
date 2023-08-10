package com.aston.bank_processing.exceptionhandler.exceptions;

public class DepositLessThanZeroException extends RuntimeException {
    public DepositLessThanZeroException() {
    }

    public DepositLessThanZeroException(String message) {
        super(message);
    }
}
