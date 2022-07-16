package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.domain.model.product.Product;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

  private UUID id;
  private Instant createDate;
  private Instant closedDate;
  private OrderStatus status;
  private List<OrderItem> items;
  private Double price;

  public Order(UUID id, Product product, Integer amount) {
    this.id = id;
    this.createDate = Instant.now();
    this.status = OrderStatus.OPEN;
    this.items = new ArrayList<>(List.of(new OrderItem(product, amount)));
    this.price = product.getPrice();
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

  public List<OrderItem> getItems() {
    return items;
  }

  public Double getPrice() {
    return price;
  }

  public void cancel() {
    this.status = OrderStatus.CANCELED;
    this.closedDate = Instant.now();
  }

  public void close() {
    this.status = OrderStatus.CLOSED;
    this.closedDate = Instant.now();
  }
}
