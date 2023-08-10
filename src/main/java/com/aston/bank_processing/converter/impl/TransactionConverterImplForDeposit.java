package com.aston.bank_processing.converter.impl;

import com.aston.bank_processing.converter.abstracts.TransactionConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.models.dto.impl.DepositDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("deposit")
public class TransactionConverterImplForDeposit implements TransactionConverter<DepositDto> {
    private final AccountService accountService;

    public TransactionConverterImplForDeposit(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public Transaction transactionCreatingDtoToTransaction(DepositDto transactionDto) {
        Transaction transaction = new Transaction();
        Account account = accountService.getAccountByAccountNumber(transactionDto.getAccount());
        transaction.setAccount(account);
        transaction.setTransationType(Transaction.TransactionType.DEPOSIT);
        transaction.setValue(transactionDto.getValue());
        return transaction;
    }
}
