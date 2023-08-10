package com.aston.bank_processing.controller;

import com.aston.bank_processing.converter.abstracts.AccountConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.models.dto.impl.AccountCreatingDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final BeneficialService beneficialService;
    private final AccountConverter accountConverter;
    private final ValidationService<Beneficial> validationService;

    public AccountController(AccountService accountService,
                             BeneficialService beneficialService,
                             AccountConverter accountConverter,
                             ValidationService<Beneficial> validationService) {
        this.accountService = accountService;
        this.accountConverter = accountConverter;
        this.validationService = validationService;
        this.beneficialService = beneficialService;
    }
    @Operation(summary = "Получить список всех аккаунтов")
    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAll();
        return ResponseEntity.ok(accounts);
    }
    @Operation(summary = "Получить конкретный аккаунт по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getById(id));
    }
    @Operation(summary = "Создать новый аккаунт")
    @PostMapping
    public ResponseEntity<Account> addAccount(@Valid @RequestBody AccountCreatingDto accountCreatingDto) {
        Beneficial beneficial = beneficialService.getBeneficialByName(accountCreatingDto.getBeneficialName());
        validationService.checkPincode(beneficial.getPincode(), accountCreatingDto.getPincode());
        Account account = accountConverter.accountCreatingDtoToAccount(accountCreatingDto);
        accountService.save(account);
        return ResponseEntity.ok(account);
    }
    @Operation(summary = "Изменить текущий аккаунт")
    @PutMapping
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account) {
        accountService.save(account);
        return ResponseEntity.ok(account);
    }
    @Operation(summary = "Удалить аккаунт по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable Long id) {
        Account account = accountService.getById(id);
        accountService.deleteById(id);
        return ResponseEntity.ok(account);
    }
}
