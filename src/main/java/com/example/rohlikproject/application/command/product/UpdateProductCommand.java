package com.example.rohlikproject.application.command.product;

import com.example.rohlikproject.application.commandbus.Command;
import java.util.UUID;

public class UpdateProductCommand extends Command {

  UUID productId;
  String name;
  Double pricePerUnit;
  Integer amount;

  public UpdateProductCommand(UUID productId, String name, Double pricePerUnit, Integer amount) {
    this.productId = productId;
    this.name = name;
    this.pricePerUnit = pricePerUnit;
    this.amount = amount;
  }

  public UUID getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public Double getPricePerUnit() {
    return pricePerUnit;
  }

  public Integer getAmount() {
    return amount;
  }
}
