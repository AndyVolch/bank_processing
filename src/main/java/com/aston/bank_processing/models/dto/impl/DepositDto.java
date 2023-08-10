package com.aston.bank_processing.models.dto.impl;

import com.aston.bank_processing.models.dto.abstracts.TransactionDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class DepositDto implements TransactionDto {
    @Pattern(regexp = "40817810[0-9]{13}", message = "Incorrect account mask")
    private String account;

    @Min(value = 0, message = "Transaction value can't be less than zero")
    private Double value;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
