package com.josetesan.ordershop.application.usecases.orders;

import com.josetesan.ordershop.application.query.order.GetOrderQuery;
import com.josetesan.ordershop.domain.model.order.Order;
import com.josetesan.ordershop.infrastructure.rest.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetOrderUseCase {

  private OrderRepository orderRepository;

  public GetOrderUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order handle(GetOrderQuery query) throws OrderNotFoundException {
    return orderRepository
        .findOrder(query.getOrderId())
        .orElseThrow(() -> new OrderNotFoundException(query.getOrderId()));
  }
}
