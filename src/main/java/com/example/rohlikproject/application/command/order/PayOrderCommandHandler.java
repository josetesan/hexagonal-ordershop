package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.PayOrderUseCase;
import com.example.rohlikproject.domain.model.order.OrderRepository;

public class PayOrderCommandHandler implements CommandHandler<PayOrderCommand> {

  private final OrderRepository repository;

  private final PayOrderUseCase useCase;

  public PayOrderCommandHandler(OrderRepository repository, PayOrderUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(PayOrderCommand command) throws Exception {}
}
