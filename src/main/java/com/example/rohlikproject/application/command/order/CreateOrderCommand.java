package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.UUID;

public class CreateOrderCommand extends Command {

  UUID productId;
  Integer amount;

  public CreateOrderCommand(UUID productId, Integer amount) {
    this.productId = productId;
    this.amount = amount;
  }

  public UUID getProductId() {
    return productId;
  }

  public Integer getAmount() {
    return amount;
  }
}
