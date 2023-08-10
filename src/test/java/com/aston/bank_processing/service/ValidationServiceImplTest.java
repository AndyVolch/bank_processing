package com.aston.bank_processing.service;

import com.aston.bank_processing.exceptionhandler.exceptions.DepositLessThanZeroException;
import com.aston.bank_processing.exceptionhandler.exceptions.IncorrectPincodeEcxeption;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceImplTest {
    @InjectMocks
    private ValidationServiceImpl<Account> service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testDepositLessThanZeroException() {
        Double value = -1D;
        assertThrows(DepositLessThanZeroException.class, () -> service.checkIfDepositAboveZero(value));
    }
    @Test
    public void checkNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> service.checkIfEntityExists(Optional.empty()));
    }
    @Test
    public void checkIncorrectPincodeException() {
        String pincode = "1234";
        String check = "0000";
        assertThrows(IncorrectPincodeEcxeption.class, () -> service.checkPincode(pincode, check));
    }
}
