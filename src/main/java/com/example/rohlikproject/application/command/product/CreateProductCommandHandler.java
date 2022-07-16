package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.CreateProductUseCase;
import com.example.rohlikproject.domain.model.product.ProductRepository;

public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

  private final ProductRepository repository;
  private final CreateProductUseCase useCase;

  public CreateProductCommandHandler(ProductRepository repository, CreateProductUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(CreateProductCommand command) throws Exception {}
}
