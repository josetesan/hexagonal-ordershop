package com.example.rohlikproject.infrastructure.rest.exceptions;

import com.example.rohlikproject.application.command.order.CreateOrder;
import java.util.List;

public class InsufficientProductStockException extends Exception {

  public InsufficientProductStockException(List<CreateOrder> insufficientProductStockList) {
    super(
        String.join(
            ",", insufficientProductStockList.stream().map(CreateOrder::toString).toList()));
  }
}
