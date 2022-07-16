package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.DeleteProductUseCase;
import com.example.rohlikproject.domain.model.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

  private final ProductRepository repository;

  private final DeleteProductUseCase useCase;

  public DeleteProductCommandHandler(ProductRepository repository, DeleteProductUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  public void handle(DeleteProductCommand command) throws Exception {}
}
