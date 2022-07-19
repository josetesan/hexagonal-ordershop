package com.josetesan.ordershop.application.command.product;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.products.CreateProductUseCase;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

  private final CreateProductUseCase useCase;

  public CreateProductCommandHandler(CreateProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(CreateProductCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
