package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.PayOrderUseCase;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PayOrderCommandHandler implements CommandHandler<PayOrderCommand> {

  private final PayOrderUseCase useCase;

  public PayOrderCommandHandler(PayOrderUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public void handle(PayOrderCommand command) throws OrderNotFoundException {
    this.useCase.handle(command);
  }
}
