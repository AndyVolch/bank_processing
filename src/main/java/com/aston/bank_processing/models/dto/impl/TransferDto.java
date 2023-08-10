package com.aston.bank_processing.models.dto.impl;

import com.aston.bank_processing.models.dto.abstracts.TransactionDto;
import jakarta.validation.constraints.Pattern;

public class TransferDto extends WithdrawDto implements TransactionDto {
    @Pattern(regexp = "40817810[0-9]{13}", message = "Incorrect account mask")
    private String accountReciever;

    public String getAccountReciever() {
        return accountReciever;
    }

    public void setAccountReciever(String accountReciever) {
        this.accountReciever = accountReciever;
    }
}
