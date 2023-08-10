package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.exceptionhandler.exceptions.DepositLessThanZeroException;
import com.aston.bank_processing.exceptionhandler.exceptions.IncorrectPincodeEcxeption;
import com.aston.bank_processing.service.abstracts.ValidationService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ValidationServiceImpl<T> implements ValidationService<T> {

    @Override
    public void checkIfDepositAboveZero(Double deposit) {
        if (deposit < 0) throw new DepositLessThanZeroException();
    }

    @Override
    public void checkIfEntityExists(Optional<T> optional) {
        if (optional.isEmpty()) throw new NoSuchElementException();
    }

    @Override
    public void checkPincode(String pincode, String control) {
        if (!pincode.equals(control)) throw new IncorrectPincodeEcxeption();
    }
}
