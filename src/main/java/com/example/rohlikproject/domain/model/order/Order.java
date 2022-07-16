package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.domain.model.product.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Order implements Serializable {
  private Instant createDate;
  private Instant closedDate;
  private OrderStatus status;
  private Set<OrderItem> items;

  public Order(Instant createDate, Instant closedDate, OrderStatus status, Set<OrderItem> items) {
    this.createDate = createDate;
    this.closedDate = closedDate;
    this.status = status;
    this.items = items;
  }

  public Order(Product product, Integer amount) {
    this.createDate = Instant.now();
    this.status = OrderStatus.OPEN;
    this.items = new HashSet<>(Set.of(new OrderItem(product, amount)));
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public Instant getClosedDate() {
    return closedDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public Double getTotalPrice() {
    var total = 0.0d;
    for (OrderItem item : items) {
      total += item.getUnitPrice() * item.getAmount();
    }
    return total;
  }

  public void cancel() {
    this.status = OrderStatus.CANCELED;
    this.closedDate = Instant.now();
  }

  public void close() {
    this.status = OrderStatus.CLOSED;
    this.closedDate = Instant.now();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Order{");
    sb.append("createDate=").append(createDate);
    sb.append(", closedDate=").append(closedDate);
    sb.append(", status=").append(status);
    sb.append(", items=").append(items);
    sb.append(", totalPrice=").append(getTotalPrice());
    sb.append('}');
    return sb.toString();
  }
}
