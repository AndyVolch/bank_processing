package com.aston.bank_processing.service.abstracts;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;

import java.util.List;

public interface TransactionService extends ReadWriteService<Transaction, Long>{
    List<Transaction> getAllByAccount(Account account);
}
