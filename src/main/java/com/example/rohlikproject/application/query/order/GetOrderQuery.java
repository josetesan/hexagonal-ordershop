package com.example.rohlikproject.application.query.order;

import com.example.rohlikproject.application.querybus.Query;
import com.example.rohlikproject.domain.model.order.Order;
import java.util.UUID;

public class GetOrderQuery extends Query<Order> {

  private UUID orderId;

  public GetOrderQuery(UUID orderId) {
    this.orderId = orderId;
  }

  public UUID getOrderId() {
    return this.orderId;
  }
}
