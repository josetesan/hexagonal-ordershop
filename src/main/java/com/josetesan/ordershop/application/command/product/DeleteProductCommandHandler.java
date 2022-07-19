package com.josetesan.ordershop.application.command.product;

import com.josetesan.ordershop.application.commandbus.CommandHandler;
import com.josetesan.ordershop.application.usecases.products.DeleteProductUseCase;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

  private final DeleteProductUseCase useCase;

  public DeleteProductCommandHandler(DeleteProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(DeleteProductCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
