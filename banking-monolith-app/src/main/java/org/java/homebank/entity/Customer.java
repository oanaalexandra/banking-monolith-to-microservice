package org.java.homebank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false,
          length = 10,
          name = "customer_name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private Account account;

  public Customer(final String name, Account account) {
    this.name = name;
    this.account = account;
  }

  public Customer() {
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(final Account account) {
    this.account = account;
  }
}
