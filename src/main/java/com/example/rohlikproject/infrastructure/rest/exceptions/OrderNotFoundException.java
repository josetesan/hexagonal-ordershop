package com.example.rohlikproject.infrastructure.rest.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends Exception {
  public OrderNotFoundException(UUID orderId) {
    super("Order " + orderId + " not found");
  }
}
