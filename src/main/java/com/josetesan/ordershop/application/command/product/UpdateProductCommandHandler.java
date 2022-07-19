package com.josetesan.ordershop.application.command.product;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.products.UpdateProductUseCase;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

  private final UpdateProductUseCase useCase;

  public UpdateProductCommandHandler(UpdateProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(UpdateProductCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
