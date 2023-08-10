package com.aston.bank_processing.models.dto.impl;

import com.aston.bank_processing.models.dto.abstracts.TransactionDto;
import jakarta.validation.constraints.Pattern;

public class WithdrawDto extends DepositDto implements TransactionDto {
    @Pattern(regexp = "[0-9]{4}", message = "Incorrect Pincode Type")
    private String pincode;

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
