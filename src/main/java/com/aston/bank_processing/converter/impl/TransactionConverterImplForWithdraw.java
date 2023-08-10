package com.aston.bank_processing.converter.impl;

import com.aston.bank_processing.converter.abstracts.TransactionConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.models.dto.impl.WithdrawDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("withdraw")
public class TransactionConverterImplForWithdraw implements TransactionConverter<WithdrawDto> {
    private final AccountService accountService;

    public TransactionConverterImplForWithdraw(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public Transaction transactionCreatingDtoToTransaction(WithdrawDto transactionDto) {
        Transaction transaction = new Transaction();
        Account account = accountService.getAccountByAccountNumber(transactionDto.getAccount());
        transaction.setAccount(account);
        transaction.setTransationType(Transaction.TransactionType.WITHDRAW);
        transaction.setValue(transactionDto.getValue());
        return transaction;
    }
}
