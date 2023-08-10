package com.aston.bank_processing.dao;

import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> getAllByAccount(Account account);
}
