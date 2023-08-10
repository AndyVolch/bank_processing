package com.aston.bank_processing.converter.impl;

import com.aston.bank_processing.converter.abstracts.TransactionConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.models.dto.impl.TransferDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("transfer")
public class TransactionConverterImplForTransfer implements TransactionConverter<TransferDto> {
    private final AccountService accountService;

    public TransactionConverterImplForTransfer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Transaction transactionCreatingDtoToTransaction(TransferDto transactionDto) {
        Transaction transaction = new Transaction();
        Account account = accountService.getAccountByAccountNumber(transactionDto.getAccount());
        transaction.setAccount(account);
        Account accountReceiver = accountService.getAccountByAccountNumber(transactionDto.getAccountReciever());
        transaction.setAccountReciever(accountReceiver);
        transaction.setTransationType(Transaction.TransactionType.TRANSFER);
        transaction.setValue(transactionDto.getValue());
        return transaction;
    }
}
