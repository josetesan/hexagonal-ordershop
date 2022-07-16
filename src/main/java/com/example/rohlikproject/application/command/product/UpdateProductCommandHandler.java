package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.UpdateProductUseCase;
import com.example.rohlikproject.domain.model.product.ProductRepository;

public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

  private final ProductRepository repository;
  private final UpdateProductUseCase useCase;

  public UpdateProductCommandHandler(ProductRepository repository, UpdateProductUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(UpdateProductCommand command) throws Exception {}
}
