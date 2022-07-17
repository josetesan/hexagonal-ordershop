package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.CreateOrderUseCase;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {

  private final CreateOrderUseCase useCase;

  public CreateOrderCommandHandler(CreateOrderUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(CreateOrderCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
