package com.example.rohlikproject.domain.model.order;

public enum OrderStatus {
  OPEN(0),
  CLOSED(1),
  CANCELED(2);
  final int value;

  OrderStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
