package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.Command;

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
