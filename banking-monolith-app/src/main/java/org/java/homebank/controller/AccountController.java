package org.java.homebank.controller;

import java.util.List;
import java.util.Optional;
import org.java.homebank.entity.Account;
import org.java.homebank.exceptions.ResourceNotFoundException;
import org.java.homebank.repository.AccountRepository;
import org.java.homebank.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private final AccountRepository repository;
  private final TransactionRepository transactionRepository;

  public AccountController(
      AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.repository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  @GetMapping("/accounts")
  List<Account> getAll() {
    return repository.findAll();
  }

  @PostMapping("/accounts")
  ResponseEntity<Account> createProduct(
      @RequestBody
      Account newProduct) {
    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newProduct));
  }

  @GetMapping("/accounts/{id}")
  ResponseEntity<Account> getOne(
      @PathVariable
      Long id) {
    Optional<Account> accountEntity = repository.findById(id);
    Account account = null;
    if (accountEntity.isPresent()) {
      account = accountEntity.get();
      account.setTransactions(transactionRepository.findTransactionByAccountName(account.getNumber()));
    }
    return ResponseEntity.ok(account);
  }

  @PutMapping("/accounts/{id}")
  ResponseEntity<Account> updateAccount(
      @RequestBody
      Account updatedAccount,
      @PathVariable
      Long id) {
    final Account account = repository.findById(id).map(p -> {
      p.setNumber(updatedAccount.getNumber());
      p.setBalance(updatedAccount.getBalance());
      p.setType(updatedAccount.getType());
      return repository.save(p);
    }).orElseThrow(ResourceNotFoundException::new);
    return ResponseEntity.ok(account);
  }

}
