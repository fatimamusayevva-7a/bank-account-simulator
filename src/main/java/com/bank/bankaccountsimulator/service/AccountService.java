package com.bank.bankaccountsimulator.service;

import com.bank.bankaccountsimulator.model.Account;
import com.bank.bankaccountsimulator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Bütün hesabları əldə et
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ID ilə hesab tap
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    // Hesab nömrəsi ilə tap
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    // Yeni hesab yarat
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    // Pul yatır
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    // Pul çək
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    // Hesab sil
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}