package com.example.rohlikproject.domain.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Product implements Serializable {

  private UUID id;
  private Integer amount;
  private Double unitPrice;
  private String name;

  @JsonCreator
  public Product(UUID id, Integer amount, Double unitPrice, String name) {

    this.id = id;
    this.amount = Objects.requireNonNull(amount, "Amount can not be null");
    this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice can not be null");
    this.name = Objects.requireNonNull(name, "name can not be null");
  }

  public Product() {}

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(getId(), product.getId())
        && Objects.equals(getAmount(), product.getAmount())
        && Objects.equals(getUnitPrice(), product.getUnitPrice())
        && Objects.equals(getName(), product.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getAmount(), getUnitPrice(), getName());
  }
}
