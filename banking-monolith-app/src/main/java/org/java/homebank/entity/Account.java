package org.java.homebank.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long identifier;

  @Column(nullable = false,
          length = 10,
          name = "account_number")
  private String number;

  @Column(nullable = false,
          length = 50,
          name = "account_type")
  private String type;

  @Column(nullable = false)
  private Float balance;

  @OneToMany(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "id")
  private List<Transaction> transactions = new ArrayList<>();

  public Account() {

  }

  public Account(
      Long identifier,
      String number,
      String type,
      Float balance) {
    this.identifier = identifier;
    this.number = number;
    this.type = type;
    this.balance = balance;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(final String number) {
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public Float getBalance() {
    return balance;
  }

  public void setBalance(final Float balance) {
    this.balance = balance;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(final List<Transaction> transactions) {
    this.transactions = transactions;
  }
}
