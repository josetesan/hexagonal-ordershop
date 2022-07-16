package com.example.rohlikproject.domain.model.product;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")
public class Product implements Serializable {

  @Id private UUID id;
  private Integer amount;
  private Double price;
  private String name;

  public Product(Integer amount, Double price, String name) {
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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("id=").append(id);
    sb.append(", amount=").append(amount);
    sb.append(", price=").append(price);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
