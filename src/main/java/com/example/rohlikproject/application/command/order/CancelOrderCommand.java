package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.UUID;

@org.jmolecules.architecture.cqrs.annotation.Command
public class CancelOrderCommand extends Command {

  private final UUID orderId;

  public CancelOrderCommand(UUID orderId) {
    this.orderId = orderId;
  }

  public UUID getOrderId() {
    return orderId;
  }
}
