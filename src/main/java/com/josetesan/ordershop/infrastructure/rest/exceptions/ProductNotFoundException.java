package com.josetesan.ordershop.infrastructure.rest.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends Exception {

  public ProductNotFoundException(UUID productId) {
    super("Product " + productId + " could not be found");
  }
}
