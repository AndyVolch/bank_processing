package com.aston.bank_processing.converter.impl;

import com.aston.bank_processing.converter.abstracts.AccountConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.models.dto.impl.AccountCreatingDto;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import com.aston.bank_processing.util.AccountNumberGenerator;
import org.springframework.stereotype.Component;

@Component
public class AccountConverterImpl implements AccountConverter {
    private final AccountNumberGenerator generator;
    private final BeneficialService beneficialService;

    public AccountConverterImpl(AccountNumberGenerator generator, BeneficialService beneficialService) {

        this.generator = generator;
        this.beneficialService = beneficialService;
    }
    @Override
    public Account accountCreatingDtoToAccount(AccountCreatingDto accountCreatingDto) {
        Account account = new Account();
        Beneficial beneficial = beneficialService
                .getBeneficialByName(accountCreatingDto.getBeneficialName());
        account.setBeneficial(beneficial);
        account.setAccount(generator.getAccountNumber());
        account.setBalance(0D);
        return account;
    }
}
