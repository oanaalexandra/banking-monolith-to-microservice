package org.java.homebank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false, length = 10)
  private String source;

  @Column(nullable = false, length = 10)
  private String destination;

  @Column(nullable = false)
  private Float amount;

  public Transaction() {
  }

  public Transaction(final String source, final String destination, final Float amount) {
    this.source = source;
    this.destination = destination;
    this.amount = amount;
  }

  public String getSource() {
    return source;
  }

  public void setSource(final String source) {
    this.source = source;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(final String destination) {
    this.destination = destination;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(final Float amount) {
    this.amount = amount;
  }
}
