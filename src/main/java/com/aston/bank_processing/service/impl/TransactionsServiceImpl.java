package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.dao.TransactionDao;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.TransactionService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionsServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final AccountService accountService;
    private final ValidationService<Transaction> validationService;

    public TransactionsServiceImpl(TransactionDao transactionDao,
                                   AccountService accountService,
                                   ValidationService<Transaction> validationService) {
        this.transactionDao = transactionDao;
        this.accountService = accountService;
        this.validationService = validationService;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionDao.findAll();
    }
    @Override
    public List<Transaction> getAllByAccount(Account account) {
        return transactionDao.getAllByAccount(account);
    }

    @Override
    public Transaction getById(Long id) {
        Optional<Transaction> transaction = transactionDao.findById(id);
        validationService.checkIfEntityExists(transaction);
        return transaction.get();
    }

    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
        switch (transaction.getTransationType().ordinal()) {
            case 0 -> accountService.deposit(transaction);
            case 1 -> accountService.withdraw(transaction);
            case 2 -> accountService.transfer(transaction);
        }
    }

    @Override
    public void deleteById(Long id) {
        transactionDao.deleteById(id);
    }
}
