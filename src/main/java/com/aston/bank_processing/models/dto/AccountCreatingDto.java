package com.aston.bank_processing.models.dto;

public class AccountCreatingDto {
    private String beneficialName;
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
