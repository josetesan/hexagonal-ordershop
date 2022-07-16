package com.example.rohlikproject.infrastructure.mapping;

import java.util.UUID;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_items")
public class OrderItemEntity {

  private UUID productId;
  private Integer amount;
  private Double unitPrice;

  public OrderItemEntity(UUID productId, Integer amount, Double unitPrice) {
    this.productId = productId;
    this.amount = amount;
    this.unitPrice = unitPrice;
  }

  public UUID getProductId() {
    return productId;
  }

  public Integer getAmount() {
    return amount;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("OrderItem{");
    sb.append("productId=").append(productId);
    sb.append(", amount=").append(amount);
    sb.append(", unitPrice=").append(unitPrice);
    sb.append('}');
    return sb.toString();
  }
}
