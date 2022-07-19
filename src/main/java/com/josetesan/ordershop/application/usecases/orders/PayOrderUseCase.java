package com.josetesan.ordershop.application.usecases.orders;

import com.josetesan.ordershop.application.command.order.PayOrderCommand;
import com.josetesan.ordershop.domain.model.order.OrderStatus;
import com.josetesan.ordershop.infrastructure.rest.exceptions.OrderNotFoundException;
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
