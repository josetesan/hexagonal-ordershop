package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.domain.model.product.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public class Order implements Serializable {
  @Id private UUID id;
  private Instant createDate;
  private Instant closedDate;

  private OrderStatus status;

  @MappedCollection(idColumn = "order_id")
  private Set<OrderItem> items;

  public Order(Product product, Integer amount) {
    this.createDate = Instant.now();
    this.status = OrderStatus.OPEN;
    this.items = new HashSet<>(Set.of(new OrderItem(product, amount)));
  }

  public UUID getId() {
    return id;
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

  public Double getPrice() {
    var total = 0.0d;
    for (OrderItem item : items) {
      total += item.getPrice() * item.getAmount();
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
    sb.append("id=").append(id);
    sb.append(", createDate=").append(createDate);
    sb.append(", closedDate=").append(closedDate);
    sb.append(", status=").append(status);
    sb.append(", items=").append(items);
    sb.append('}');
    return sb.toString();
  }
}
