package com.aston.bank_processing.controller;

import com.aston.bank_processing.converter.AccountConverter;
import com.aston.bank_processing.models.Account;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.models.dto.AccountCreatingDto;
import com.aston.bank_processing.service.abstracts.AccountService;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final BeneficialService beneficialService;
    private final AccountConverter accountConverter;

    public AccountController(AccountService accountService,
                             BeneficialService beneficialService,
                             AccountConverter accountConverter) {
        this.accountService = accountService;
        this.beneficialService=beneficialService;
        this.accountConverter=accountConverter;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody AccountCreatingDto accountCreatingDto) {
        Optional<Beneficial> beneficial = beneficialService.
                getBeneficialByName(accountCreatingDto.getBeneficialName());
        if (beneficial.isPresent()) {
            if (accountCreatingDto.getPincode().equals(beneficial.get().getPincode())) {
                Account account = accountConverter.accountCreatingDtoToAccount(accountCreatingDto);
                accountService.save(account);
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        accountService.save(account);
        return ResponseEntity.ok(account);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getById(id);
        if (account.isPresent()) {
            accountService.deleteById(id);
            return ResponseEntity.ok(account.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
