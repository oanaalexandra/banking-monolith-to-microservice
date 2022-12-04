package org.java.homebank.repository;

import org.java.homebank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query("select acc from accounts acc where acc.number=?1")
  Account findAccountByNumber(String accountNumber);

}
