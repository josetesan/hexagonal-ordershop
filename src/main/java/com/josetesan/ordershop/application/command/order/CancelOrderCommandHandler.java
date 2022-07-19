package com.josetesan.ordershop.application.command.order;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.orders.CancelOrderUseCase;
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
    this.useCase.handle(command.getOrderId());
  }
}
