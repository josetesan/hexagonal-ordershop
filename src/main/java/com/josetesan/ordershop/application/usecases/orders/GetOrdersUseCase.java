package com.josetesan.ordershop.application.usecases.orders;

import com.josetesan.ordershop.domain.model.order.Order;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetOrdersUseCase {

  private final OrderRepository orderRepository;

  public GetOrdersUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> handle() {
    return orderRepository.findAll();
  }

  public List<Order> handle(Integer maxMinutesDue) {
    return orderRepository.findOrdersDue(maxMinutesDue);
  }
}
