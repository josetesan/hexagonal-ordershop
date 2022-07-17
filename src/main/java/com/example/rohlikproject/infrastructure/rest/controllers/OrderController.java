package com.example.rohlikproject.infrastructure.rest.controllers;

import com.example.rohlikproject.application.command.order.CancelOrderCommand;
import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.application.command.order.PayOrderCommand;
import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.query.order.GetOrdersQuery;
import com.example.rohlikproject.application.querybus.QueryBus;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.infrastructure.rest.Constants;
import com.example.rohlikproject.infrastructure.rest.exceptions.InsufficientProductStockException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Object> createOrder(@RequestBody CreateOrderCommand command)
      throws Exception {
    try {
      this.commandBus.handle(command);
    } catch (InsufficientProductStockException ipse) {
      return ResponseEntity.badRequest()
          .headers(httpHeaders -> httpHeaders.add(Constants.X_ERROR_HEADER, "not enough stock"))
          .body(ipse.getMessage());
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<Order>> retrieveOrders() throws Exception {
    GetOrdersQuery query = new GetOrdersQuery();
    List<Order> orders = this.queryBus.handle(query);
    return ResponseEntity.ok().body(orders);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> cancelOrder(@PathVariable("id") UUID id) {
    try {
      this.commandBus.handle(new CancelOrderCommand(id));
    } catch (Exception onfe) {
      return ResponseEntity.badRequest()
          .headers(httpHeaders -> httpHeaders.add(Constants.X_ERROR_HEADER, onfe.getMessage()))
          .build();
    }
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> payOrder(@PathVariable("id") UUID id) {
    try {
      this.commandBus.handle(new PayOrderCommand(id));
    } catch (Exception onfe) {
      return ResponseEntity.badRequest()
          .headers(httpHeaders -> httpHeaders.add(Constants.X_ERROR_HEADER, onfe.getMessage()))
          .build();
    }
    return ResponseEntity.accepted().build();
  }
}
