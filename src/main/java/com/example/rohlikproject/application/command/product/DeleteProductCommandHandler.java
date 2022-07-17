package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.DeleteProductUseCase;
import com.example.rohlikproject.infrastructure.repository.database.SpringProductRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

  private final SpringProductRepository repository;

  private final DeleteProductUseCase useCase;

  public DeleteProductCommandHandler(
      SpringProductRepository repository, DeleteProductUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(DeleteProductCommand command) throws Exception {}
}
