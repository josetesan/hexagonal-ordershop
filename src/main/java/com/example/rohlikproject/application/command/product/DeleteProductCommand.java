package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.UUID;

@org.jmolecules.architecture.cqrs.annotation.Command
public class DeleteProductCommand extends Command {

  UUID productId;

  public DeleteProductCommand(UUID productId) {
    this.productId = productId;
  }

  public UUID getProductId() {
    return productId;
  }
}
