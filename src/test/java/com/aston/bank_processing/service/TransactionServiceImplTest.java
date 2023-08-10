package com.aston.bank_processing.service;
import com.aston.bank_processing.dao.TransactionDao;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import com.aston.bank_processing.service.impl.TransactionsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {
    @Mock
    private TransactionDao transactionDao;

    @Mock
    private AccountService accountService;

    @Mock
    private ValidationService<Transaction> validationService;

    @InjectMocks
    private TransactionsServiceImpl transactionsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTransactions() {
        when(transactionDao.findAll()).thenReturn(List.of(new Transaction(), new Transaction()));

        List<Transaction> transactions = transactionsService.getAll();

        assertThat(transactions).hasSize(2);
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = new Transaction();
        when(transactionDao.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction retrievedTransaction = transactionsService.getById(1L);

        assertThat(retrievedTransaction).isEqualTo(transaction);
    }

    @Test
    public void testSaveTransactionDeposit() {
        Transaction transaction = new Transaction();
        transaction.setTransationType(Transaction.TransactionType.DEPOSIT);
        when(transactionDao.save(any())).thenReturn(transaction);

        transactionsService.save(transaction);

        verify(accountService, times(1)).deposit(transaction);
    }

    @Test
    public void testSaveTransactionWithdraw() {
        Transaction transaction = new Transaction();
        transaction.setTransationType(Transaction.TransactionType.WITHDRAW);
        when(transactionDao.save(any())).thenReturn(transaction);

        transactionsService.save(transaction);

        verify(accountService, times(1)).withdraw(transaction);
    }

    @Test
    public void testSaveTransactionTransfer() {
        Transaction transaction = new Transaction();
        transaction.setTransationType(Transaction.TransactionType.TRANSFER);
        when(transactionDao.save(any())).thenReturn(transaction);

        transactionsService.save(transaction);

        verify(accountService, times(1)).transfer(transaction);
    }
}
