package com.aston.bank_processing.service.abstracts;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;

public interface AccountService extends ReadWriteService<Account, Long>{
    Account getAccountByAccountNumber(String accountNumber);
    void deposit(Transaction transaction);
    void withdraw(Transaction transaction);
    void transfer(Transaction transaction);
}
