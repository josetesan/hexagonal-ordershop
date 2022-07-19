package com.josetesan.ordershop.application.command.product;

import com.josetesan.ordershop.application.commandbus.Command;
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
