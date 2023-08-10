package com.aston.bank_processing.exceptionhandler;

import com.aston.bank_processing.exceptionhandler.exceptions.DepositLessThanZeroException;
import com.aston.bank_processing.exceptionhandler.exceptions.IncorrectPincodeEcxeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppErrorMessage> noSuchElementException() {
        return new ResponseEntity<>(new AppErrorMessage(HttpStatus.NOT_FOUND.value(),
                "Element hasn't been found in DB"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DepositLessThanZeroException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorMessage> depositLessThanZeroException() {
        return new ResponseEntity<>(new AppErrorMessage(HttpStatus.NOT_FOUND.value(),
                "Deposit can't be less than zero"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IncorrectPincodeEcxeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorMessage> incorrectPincode() {
        return new ResponseEntity<>(new AppErrorMessage(HttpStatus.NOT_FOUND.value(),
                "Incorrect pincode"), HttpStatus.BAD_REQUEST);
    }
}
