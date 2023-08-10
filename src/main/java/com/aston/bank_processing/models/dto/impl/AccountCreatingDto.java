package com.aston.bank_processing.models.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AccountCreatingDto {

    @NotBlank(message = "There is no valid name")
    private String beneficialName;
    @Pattern(regexp = "[0-9]{4}", message = "Incorrect Pincode Type")
    private String pincode;

    public String getBeneficialName() {
        return beneficialName;
    }

    public void setBeneficialName(String beneficialName) {
        this.beneficialName = beneficialName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
