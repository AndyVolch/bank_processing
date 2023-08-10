package com.aston.bank_processing.service;

import com.aston.bank_processing.dao.AccountDao;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.service.impl.AccountServiceImpl;
import com.aston.bank_processing.service.abstracts.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class AccountServiceImplTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private ValidationService<Account> validationService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts() {

        when(accountDao.findAll()).thenReturn(List.of(new Account(), new Account()));
        List<Account> accounts = accountService.getAll();
        assertThat(accounts).hasSize(2);
    }

    @Test
    public void testGetAccountById() {

        Account account = new Account();
        when(accountDao.findById(1L)).thenReturn(Optional.of(account));

        Account retrievedAccount = accountService.getById(1L);

        assertThat(retrievedAccount).isEqualTo(account);
    }

    @Test
    public void testGetAccountByIdIdAbsent() {
        when(accountDao.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> accountService.getById(1L));
    }

    @Test
    public void testGetAccountByAccountNumber() {
        String testNumber = "408178101508513688144";
        Account account = new Account();
        account.setAccount(testNumber);
        when(accountDao.getAccountByAccountNumber(testNumber)).thenReturn(Optional.of(account));

        Account check = accountService.getAccountByAccountNumber(testNumber);

        assertThat(check.getAccount()).isEqualTo(testNumber);
    }
    @Test
    public void testGetAccountByAccountNumberIfAbsent() {
        String testNumber = "408178101508513688144";
        Account account = new Account();
        account.setAccount(testNumber);
        when(accountDao.getAccountByAccountNumber(testNumber)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> accountService.getAccountByAccountNumber(testNumber));
    }

    @Test
    public void testDeposit() {

        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setValue(50.0);
        when(accountDao.findById(1L)).thenReturn(Optional.of(account));

        accountService.deposit(transaction);

        assertThat(account.getBalance()).isEqualTo(150.0);
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setValue(50.0);
        when(accountDao.findById(1L)).thenReturn(Optional.of(account));


        accountService.withdraw(transaction);

        assertThat(account.getBalance()).isEqualTo(50.0);
    }

    @Test
    public void testTransfer() {
        Account sender = new Account();
        sender.setId(1L);
        sender.setBalance(100.0);
        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setBalance(50.0);
        Transaction transaction = new Transaction();
        transaction.setAccount(sender);
        transaction.setAccountReciever(receiver);
        transaction.setValue(30.0);
        when(accountDao.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountDao.findById(receiver.getId())).thenReturn(Optional.of(receiver));

        accountService.transfer(transaction);
        assertAll(
                () -> assertThat(sender.getBalance()).isEqualTo(70.0),
                () -> assertThat(receiver.getBalance()).isEqualTo(80.0)
        );
    }
}
