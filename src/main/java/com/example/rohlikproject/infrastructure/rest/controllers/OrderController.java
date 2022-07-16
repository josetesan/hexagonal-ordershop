package com.example.rohlikproject.infrastructure.rest.controllers;

import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.querybus.QueryBus;
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
}
