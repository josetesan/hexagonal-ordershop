package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.domain.model.product.Product;
import java.util.Objects;
import java.util.UUID;

public class OrderItem {

  private final UUID productId;
  private final Integer amount;
  private final Double unitPrice;

  public OrderItem(Product product, Integer amount) {
    this.productId = product.getId();
    this.amount = amount;
    this.unitPrice = product.getUnitPrice();
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

  public Double getTotalPrice() {
    return unitPrice * amount;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("OrderItem{");
    sb.append("productId=").append(productId);
    sb.append(", amount=").append(amount);
    sb.append(", unitPrice=").append(unitPrice);
    sb.append(", totalPrice=").append(getTotalPrice());
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItem orderItem = (OrderItem) o;
    return Objects.equals(getProductId(), orderItem.getProductId())
        && Objects.equals(getAmount(), orderItem.getAmount())
        && Objects.equals(getUnitPrice(), orderItem.getUnitPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getProductId(), getAmount(), getUnitPrice());
  }
}
