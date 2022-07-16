package com.example.rohlikproject.domain.model.product;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

  private UUID id;
  private Integer amount;
  private Double unitPrice;
  private String name;

  public Product(UUID id, Integer amount, Double unitPrice, String name) {
    this.id = id;
    this.amount = amount;
    this.unitPrice = unitPrice;
    this.name = name;
  }

  public Product(Integer amount, Double unitPrice, String name) {
    this.amount = amount;
    this.unitPrice = unitPrice;
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public Integer getAmount() {
    return amount;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("id=").append(id);
    sb.append(", amount=").append(amount);
    sb.append(", unitPrice=").append(unitPrice);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
