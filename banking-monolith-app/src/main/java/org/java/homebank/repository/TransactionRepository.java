package org.java.homebank.repository;

import java.util.List;
import org.java.homebank.entity.Account;
import org.java.homebank.entity.Customer;
import org.java.homebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query("select tran from transactions tran where tran.source=?1 or tran.destination=?1")
  List<Transaction> findTransactionByAccountName(String accountNumber);

}
