package com.aston.bank_processing.dao;

import com.aston.bank_processing.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    Optional<Account> getAccountByAccountNumber(String accountNumber);
}
