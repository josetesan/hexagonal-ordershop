package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.CancelOrderUseCase;
import com.example.rohlikproject.domain.model.order.OrderRepository;

public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {

  private final OrderRepository repository;
  private final CancelOrderUseCase useCase;

  public CancelOrderCommandHandler(OrderRepository repository, CancelOrderUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(CancelOrderCommand command) throws Exception {}
}
