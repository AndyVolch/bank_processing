package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.dao.AccountDao;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.service.abstracts.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> getAll() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> getById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountDao.deleteById(id);
    }
}
