package com.bank.bankaccountsimulator.controller;

import com.bank.bankaccountsimulator.model.Account;
import com.bank.bankaccountsimulator.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // GET /api/accounts — bütün hesablar
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // GET /api/accounts/{id} — ID ilə hesab
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/accounts — yeni hesab yarat
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // POST /api/accounts/{accountNumber}/deposit — pul yatır
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Account> deposit(
            @PathVariable String accountNumber,
            @RequestBody Map<String, BigDecimal> request) {
        BigDecimal amount = request.get("amount");
        return ResponseEntity.ok(accountService.deposit(accountNumber, amount));
    }

    // POST /api/accounts/{accountNumber}/withdraw — pul çək
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<Account> withdraw(
            @PathVariable String accountNumber,
            @RequestBody Map<String, BigDecimal> request) {
        try {
            BigDecimal amount = request.get("amount");
            return ResponseEntity.ok(accountService.withdraw(accountNumber, amount));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/accounts/{id} — hesab sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}