package com.example.rohlikproject.domain.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

public class Product implements Serializable {

  final Long id;
  final Timestamp createDate;
  final Integer amount;
  final Double price;
  final String name;

  @JsonCreator
  public Product(Long id, String name, Integer amount, Double price) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.price = price;
    this.createDate = Timestamp.from(Instant.now());
  }

  public Long id() {
    return id;
  }

  public Timestamp createDate() {
    return createDate;
  }

  public Integer amount() {
    return amount;
  }

  public Double price() {
    return price;
  }

  public String name() {
    return name;
  }
}
