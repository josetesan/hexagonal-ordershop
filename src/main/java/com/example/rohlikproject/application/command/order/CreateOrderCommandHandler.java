package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.CreateOrderUseCase;
import com.example.rohlikproject.domain.model.order.OrderRepository;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {

  private final OrderRepository repository;
  private final CreateOrderUseCase useCase;

  public CreateOrderCommandHandler(OrderRepository repository, CreateOrderUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(CreateOrderCommand command) throws Exception {}
}
