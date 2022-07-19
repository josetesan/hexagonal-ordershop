package com.josetesan.ordershop.application.command.order;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.orders.PayOrderUseCase;
import com.josetesan.ordershop.infrastructure.rest.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PayOrderCommandHandler implements CommandHandler<PayOrderCommand> {

  private final PayOrderUseCase useCase;

  public PayOrderCommandHandler(PayOrderUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(PayOrderCommand command) throws OrderNotFoundException {
    this.useCase.handle(command);
  }
}
