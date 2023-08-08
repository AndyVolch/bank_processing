package com.aston.bank_processing.converter;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.dto.AccountCreatingDto;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import com.aston.bank_processing.util.AccountNumberGenerator;
import org.springframework.stereotype.Component;

@Component
public class AccountConverterImpl implements AccountConverter{
    private final AccountNumberGenerator generator;
    private final BeneficialService beneficialService;

    public AccountConverterImpl(AccountNumberGenerator generator, BeneficialService beneficialService) {

        this.generator = generator;
        this.beneficialService = beneficialService;
    }
    @Override
    public Account accountCreatingDtoToAccount(AccountCreatingDto accountCreatingDto) {
        Account account = new Account();
        account.setAccount(generator.getAccountNumber());
        account.setBalance(0D);
        account.setBeneficial(beneficialService
                .getBeneficialByName(accountCreatingDto.getBeneficialName()).get());
        return account;
    }
}
