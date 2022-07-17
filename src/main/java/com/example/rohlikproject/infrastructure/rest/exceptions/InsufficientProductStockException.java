package com.example.rohlikproject.infrastructure.rest.exceptions;

public class InsufficientProductStockException extends Exception {

  public InsufficientProductStockException(String message) {
    super(message);
  }
}
