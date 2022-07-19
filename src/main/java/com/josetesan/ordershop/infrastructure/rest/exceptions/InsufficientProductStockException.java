package com.josetesan.ordershop.infrastructure.rest.exceptions;

public class InsufficientProductStockException extends Exception {

  public InsufficientProductStockException(String message) {
    super(message);
  }
}
