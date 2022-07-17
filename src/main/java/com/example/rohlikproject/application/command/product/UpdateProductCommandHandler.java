package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.CommandHandler;
import com.example.rohlikproject.application.usecases.products.UpdateProductUseCase;
import com.example.rohlikproject.infrastructure.repository.database.SpringProductRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

  private final SpringProductRepository repository;
  private final UpdateProductUseCase useCase;

  public UpdateProductCommandHandler(
      SpringProductRepository repository, UpdateProductUseCase useCase) {
    this.repository = repository;
    this.useCase = useCase;
  }

  @Override
  @org.jmolecules.architecture.cqrs.annotation.CommandHandler
  public void handle(UpdateProductCommand command) throws Exception {}
}
