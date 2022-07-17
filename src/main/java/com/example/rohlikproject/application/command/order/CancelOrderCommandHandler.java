package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.CancelOrderUseCase;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {

  private final CancelOrderUseCase useCase;

  public CancelOrderCommandHandler(CancelOrderUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(CancelOrderCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
