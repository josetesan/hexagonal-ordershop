package com.example.rohlikproject.application.command.order;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.List;

@org.jmolecules.architecture.cqrs.annotation.Command
public class CreateOrderCommand extends Command {

  private final List<CreateOrder> createOrder;

  public CreateOrderCommand(List<CreateOrder> createOrder) {
    this.createOrder = createOrder;
  }

  public List<CreateOrder> getCreateOrder() {
    return createOrder;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CreateOrderCommand{");
    sb.append("createOrder=").append(createOrder);
    sb.append('}');
    return sb.toString();
  }
}
