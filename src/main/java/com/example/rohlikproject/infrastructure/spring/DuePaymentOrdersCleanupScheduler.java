package com.example.rohlikproject.infrastructure.spring;

import com.example.rohlikproject.application.command.order.CancelOrderCommand;
import com.example.rohlikproject.application.usecases.orders.CancelOrderUseCase;
import com.example.rohlikproject.application.usecases.orders.OrderRepository;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DuePaymentOrdersCleanupScheduler {

  private final CancelOrderUseCase cancelOrderUseCase;
  private final OrderRepository orderRepository;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(DuePaymentOrdersCleanupScheduler.class);

  @Value("${orders.maxtime.due.minutes}")
  private Integer maxTimeOrdersDueMinutes;

  public DuePaymentOrdersCleanupScheduler(CancelOrderUseCase cancelOrderUseCase, OrderRepository orderRepository) {
    this.cancelOrderUseCase = cancelOrderUseCase;
    this.orderRepository = orderRepository;
  }

  @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
  @Async
  public void cleanupOpenOrdersNotClosed() {

    List<Order> orders = this.orderRepository.findOrdersDue(maxTimeOrdersDueMinutes);
    orders.forEach(
        order -> {
          try {
            this.cancelOrderUseCase.handle(new CancelOrderCommand(order.getId()));
          } catch (OrderNotFoundException e) {
            // we can safely skip there, no order to cancel, maybe already satisfied.
          }
        });
    LOGGER.info("Cleaned up {} orders", orders.size());
  }
}
