package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.CancelOrderUseCase;
import com.example.rohlikproject.infrastructure.repository.database.SpringOrderRepository;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {

  private final SpringOrderRepository repository;
  private final CancelOrderUseCase useCase;

  public CancelOrderCommandHandler(SpringOrderRepository repository, CancelOrderUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(CancelOrderCommand command) throws Exception {}
}
