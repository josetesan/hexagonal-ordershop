package com.example.rohlikproject.domain.model.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;

public class Order implements Serializable {

  @Id final Long id;
  final Instant createDate;
  Instant closedDate;
  OrderStatus status;

  @JsonCreator
  public Order(Long id, Instant createDate) {
    this.id = id;
    this.createDate = createDate;
    this.status = OrderStatus.OPEN;
  }

  public Long id() {
    return id;
  }

  public Instant createDate() {
    return createDate;
  }

  public Instant closedDate() {
    return closedDate;
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
