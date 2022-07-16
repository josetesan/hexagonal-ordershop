package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.CreateProductUseCase;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

  private final CreateProductUseCase useCase;

  public CreateProductCommandHandler(CreateProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public void handle(CreateProductCommand command) throws Exception {
    this.useCase.handle(command);
  }
}
