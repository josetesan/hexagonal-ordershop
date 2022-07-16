package com.example.rohlikproject.domain.model.product;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

  private UUID id;
  private Integer amount;
  private Double price;
  private String name;

  public Product(UUID id, Integer amount, Double price, String name) {
    this.id = id;
    this.amount = amount;
    this.price = price;
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public Integer getAmount() {
    return amount;
  }

  public Double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }
}
