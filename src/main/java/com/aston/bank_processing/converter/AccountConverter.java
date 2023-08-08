package com.aston.bank_processing.converter;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.dto.AccountCreatingDto;

public interface AccountConverter {
    Account accountCreatingDtoToAccount(AccountCreatingDto accountCreatingDto);
}
