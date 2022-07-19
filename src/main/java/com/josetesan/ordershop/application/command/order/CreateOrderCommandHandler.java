package com.josetesan.ordershop.application.command.order;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.orders.CreateOrderUseCase;
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
