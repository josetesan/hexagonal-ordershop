package com.josetesan.ordershop.application.command.product;

import com.josetesan.ordershop.application.commandbus.Command;

@org.jmolecules.architecture.cqrs.annotation.Command
public class CreateProductCommand extends Command {

  private final String name;
  private final Double price;
  private final Integer amount;

  public CreateProductCommand(String name, Double price, Integer amount) {
    this.name = name;
    this.price = price;
    this.amount = amount;
  }

  public String name() {
    return name;
  }

  public Double price() {
    return price;
  }

  public Integer amount() {
    return amount;
  }
}
