package com.aston.bank_processing.controller;

import com.aston.bank_processing.converter.abstracts.TransactionConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.models.dto.impl.DepositDto;
import com.aston.bank_processing.models.dto.impl.TransferDto;
import com.aston.bank_processing.models.dto.impl.WithdrawDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.TransactionService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountId}/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionConverter<DepositDto> depositDtoTransactionConverter;
    private final TransactionConverter<WithdrawDto> withdrawDtoTransactionConverter;
    private final TransactionConverter<TransferDto> transferDtoTransactionConverter;
    private final AccountService accountService;
    private final ValidationService<Transaction> validationService;

    public TransactionController(TransactionService transactionService,
                                 @Qualifier("deposit")
                                 TransactionConverter<DepositDto> depositDtoTransactionConverter,
                                 @Qualifier("withdraw")
                                 TransactionConverter<WithdrawDto> withdrawDtoTransactionConverter,
                                 @Qualifier("transfer")
                                 TransactionConverter<TransferDto> transferDtoTransactionConverter,
                                 AccountService accountService,
                                 ValidationService<Transaction> validationService) {
        this.transactionService = transactionService;
        this.depositDtoTransactionConverter = depositDtoTransactionConverter;
        this.withdrawDtoTransactionConverter = withdrawDtoTransactionConverter;
        this.transferDtoTransactionConverter = transferDtoTransactionConverter;
        this.accountService = accountService;
        this.validationService = validationService;
    }
    @Operation(summary = "Получить все транзакции определённого аккаунта")
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Long accountId) {
        Account account = accountService.getById(accountId);
        List<Transaction> transactions = transactionService.getAllByAccount(account);
        return ResponseEntity.ok(transactions);

    }
    @Operation(summary = "Получить транзакцию по ID")
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionsById(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getById(transactionId);
        return ResponseEntity.ok(transaction);
    }
    @Operation(summary = "Пополнение счёта")
    @PostMapping("/deposit")
    public ResponseEntity<Transaction> addDepositTransaction(@Valid @RequestBody DepositDto transactionDto) {
        Transaction transaction = depositDtoTransactionConverter.
                transactionCreatingDtoToTransaction(transactionDto);
        transactionService.save(transaction);
        return ResponseEntity.ok(transaction);
    }
    @Operation(summary = "Списание со счёта")
    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> addWithdrawTransaction(@Valid @RequestBody WithdrawDto transactionDto) {
        Account account = accountService.getAccountByAccountNumber(transactionDto.getAccount());
        Beneficial beneficial = account.getBeneficial();
        validationService.checkPincode(beneficial.getPincode(), transactionDto.getPincode());
        Transaction transaction = withdrawDtoTransactionConverter.
                transactionCreatingDtoToTransaction(transactionDto);
        transactionService.save(transaction);
        return ResponseEntity.ok(transaction);
    }
    @Operation(summary = "Перевод с одного счёта на другой")
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> addTransferTransaction(@Valid @RequestBody TransferDto transactionDto) {
        Account account = accountService.getAccountByAccountNumber(transactionDto.getAccount());
        Beneficial beneficial = account.getBeneficial();
        validationService.checkPincode(beneficial.getPincode(), transactionDto.getPincode());
        Transaction transaction = transferDtoTransactionConverter.
                transactionCreatingDtoToTransaction(transactionDto);
        transactionService.save(transaction);
        return ResponseEntity.ok(transaction);
    }
}
