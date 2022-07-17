package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.query.order.GetOrderQuery;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
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
