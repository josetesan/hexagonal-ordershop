package com.example.rohlikproject.infrastructure.mapping;

import com.example.rohlikproject.domain.model.order.OrderStatus;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public class OrderEntity {

  @Id private UUID id;
  private Instant createDate;
  private Instant closedDate;
  private OrderStatus status;

  @MappedCollection(idColumn = "order_id")
  private Set<OrderItemEntity> items;

  public OrderEntity(
      Instant createDate, Instant closedDate, OrderStatus status, Set<OrderItemEntity> items) {
    this.createDate = createDate;
    this.closedDate = closedDate;
    this.status = status;
    this.items = items;
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

  public Set<OrderItemEntity> getItems() {
    return items;
  }
}
