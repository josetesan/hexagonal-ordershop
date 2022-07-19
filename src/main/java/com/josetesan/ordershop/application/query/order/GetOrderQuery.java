package com.josetesan.ordershop.application.query.order;

import com.josetesan.ordershop.application.querybus.Query;
import com.josetesan.ordershop.domain.model.order.Order;
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
