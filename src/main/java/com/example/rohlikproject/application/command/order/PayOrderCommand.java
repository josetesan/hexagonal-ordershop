package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.UUID;

public class PayOrderCommand extends Command {

  private UUID orderId;

  public PayOrderCommand(UUID orderId) {
    this.orderId = orderId;
  }

  public UUID getOrderId() {
    return orderId;
  }
}
