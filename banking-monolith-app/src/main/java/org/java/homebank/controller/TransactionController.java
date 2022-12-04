package org.java.homebank.controller;

import java.util.List;
import org.java.homebank.entity.Account;
import org.java.homebank.entity.Transaction;
import org.java.homebank.exceptions.ResourceNotFoundException;
import org.java.homebank.repository.AccountRepository;
import org.java.homebank.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

  private final TransactionRepository repository;

  private final AccountRepository accountRepository;

  public TransactionController(
      TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.repository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  @GetMapping("/transactions")
  List<Transaction> getAll() {
    return repository.findAll();
  }

  @PostMapping("/transactions")
  ResponseEntity<Transaction> createOrder(
      @RequestBody
      Transaction newTransaction) {
    return ResponseEntity.status(HttpStatus.CREATED).body(createNewTransaction(newTransaction));
  }

  @GetMapping("/orders/{id}")
  ResponseEntity<Transaction> getOne(
      @PathVariable
      Long id) {
    final Transaction transaction = repository
        .findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    return ResponseEntity.ok(transaction);
  }

  @DeleteMapping("/orders/{id}")
  void deleteOrder(
      @PathVariable
      Long id) {
    repository.deleteById(id);
  }

  private Transaction createNewTransaction(Transaction transaction) {
    Account existingAccount = accountRepository.findAccountByNumber(transaction.getSource());
    existingAccount.setBalance(existingAccount.getBalance() - transaction.getAmount());
    accountRepository.save(existingAccount);
    return transaction;
  }
}
