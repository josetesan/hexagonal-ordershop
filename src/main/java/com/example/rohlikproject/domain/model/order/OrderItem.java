package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.domain.model.product.Product;
import java.util.UUID;

public class OrderItem {
  private final UUID productId;
  private final Integer amount;
  private final Double price;

  public OrderItem(Product product, Integer amount) {
    this.productId = product.getId();
    this.amount = amount;
    this.price = product.getPrice() * amount;
  }

  public UUID getProductId() {
    return productId;
  }

  public Integer getAmount() {
    return amount;
  }

  public Double getPrice() {
    return price;
  }
}
