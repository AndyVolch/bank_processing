package com.aston.bank_processing.service.abstracts;
import java.util.Optional;

public interface ValidationService<T> {
    void checkIfDepositAboveZero(Double deposit);
    void checkIfEntityExists(Optional<T> optional);

    void checkPincode(String pincode, String control);
}
