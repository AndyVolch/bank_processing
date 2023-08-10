package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.dao.AccountDao;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final ValidationService<Account> validationService;

    public AccountServiceImpl(AccountDao accountDao, ValidationService<Account> validationService) {

        this.accountDao = accountDao;
        this.validationService = validationService;
    }

    @Override
    public List<Account> getAll() {
        return accountDao.findAll();
    }

    @Override
    public Account getById(Long id) {
        Optional<Account> account = accountDao.findById(id);
        validationService.checkIfEntityExists(account);
        return account.get();
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        Optional<Account> account = accountDao.getAccountByAccountNumber(accountNumber);
        validationService.checkIfEntityExists(account);
        return account.get();
    }

    @Override
    public void deposit(Transaction transaction) {
        Optional<Account> account = accountDao.findById(transaction.getAccount().getId());
        validationService.checkIfEntityExists(account);
        Double currentBalance = account.get().getBalance();
        account.get().setBalance(currentBalance + transaction.getValue());
    }

    @Override
    public void withdraw(Transaction transaction) {
        Optional<Account> account = accountDao.findById(transaction.getAccount().getId());
        validationService.checkIfEntityExists(account);
        Double currentBalance = account.get().getBalance();
        validationService.checkIfDepositAboveZero(currentBalance - transaction.getValue());
        account.get().setBalance(currentBalance - transaction.getValue());
    }

    @Override
    public void transfer(Transaction transaction) {
        withdraw(transaction);
        Optional<Account> accountReciever = accountDao.
                findById(transaction.getAccountReciever().getId());
        validationService.checkIfEntityExists(accountReciever);
        Double currentBalance = accountReciever.get().getBalance();
        accountReciever.get().setBalance(currentBalance + transaction.getValue());
    }
}
