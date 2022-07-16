package com.example.rohlikproject.infrastructure.rest.controllers;

import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.querybus.QueryBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

  private CommandBus commandBus;
  private QueryBus queryBus;

  public OrderController(CommandBus commandBus, QueryBus queryBus) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody CreateOrderCommand command)
      throws Exception {
    this.commandBus.handle(command);
    return ResponseEntity.ok().build();
  }
}
