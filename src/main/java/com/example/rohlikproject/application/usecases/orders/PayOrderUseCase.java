package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.PayOrderCommand;
import com.example.rohlikproject.domain.model.order.OrderStatus;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PayOrderUseCase {
  private final OrderRepository orderRepository;

  public PayOrderUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  public void handle(PayOrderCommand command) throws OrderNotFoundException {

    this.orderRepository
        .findOrder(command.getOrderId())
        .filter(theOrder -> theOrder.getStatus().equals(OrderStatus.OPEN))
        .orElseThrow(() -> new OrderNotFoundException(command.getOrderId()));

    this.orderRepository.closeOrder(command.getOrderId());
  }
}
