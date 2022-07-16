package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.orders.PayOrderUseCase;
import com.example.rohlikproject.infrastructure.repository.database.SpringOrderRepository;
import org.springframework.stereotype.Component;

@Component
public class PayOrderCommandHandler implements CommandHandler<PayOrderCommand> {

  private final SpringOrderRepository repository;

  private final PayOrderUseCase useCase;

  public PayOrderCommandHandler(SpringOrderRepository repository, PayOrderUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(PayOrderCommand command) throws Exception {}
}
