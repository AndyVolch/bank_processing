package com.aston.bank_processing.converter.abstracts;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.dto.impl.AccountCreatingDto;

public interface AccountConverter {
    Account accountCreatingDtoToAccount(AccountCreatingDto accountCreatingDto);
}
