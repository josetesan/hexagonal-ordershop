package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.CreateOrder;
import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.application.usecases.products.ProductRepository;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.exceptions.InsufficientProductStockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  private final ObjectMapper objectMapper;

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderUseCase.class);

  public CreateOrderUseCase(
      OrderRepository orderRepository,
      ProductRepository productRepository,
      ObjectMapper objectMapper) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.objectMapper = objectMapper;
  }

  @Transactional(rollbackFor = {InsufficientProductStockException.class})
  public void handle(CreateOrderCommand command) throws InsufficientProductStockException {

    var insufficientProductStockList = new ArrayList<CreateOrder>();
    var requestOrder = new ArrayList<ProductRequestDto>();
    for (CreateOrder createOrder : command.getCreateOrder()) {

      var optionalStock = this.productRepository.findProduct(createOrder.productId());
      if (optionalStock.isPresent()) {
        var stock = optionalStock.get();
        if (stock.getAmount() < createOrder.amount()) {
          insufficientProductStockList.add(
              new CreateOrder(stock.getId(), createOrder.amount() - stock.getAmount()));
        } else {
          requestOrder.add(
              new ProductRequestDto(
                  stock.getId(),
                  stock.getUnitPrice(),
                  createOrder.amount(),
                  stock.getAmount(),
                  stock.getName()));

          var newProduct =
              new Product(
                  stock.getId(),
                  stock.getAmount() - createOrder.amount(),
                  stock.getUnitPrice(),
                  stock.getName());
          productRepository.updateProduct(newProduct);
        }
      } else {
        insufficientProductStockList.add(createOrder);
      }
    }
    // all requests are satisfied
    if (insufficientProductStockList.isEmpty()) {
      orderRepository.createOrder(new Order(requestOrder));
    } else {
      LOGGER.warn("Order not created, not enough stock");
      String error = "Not enough stock";
      try {
        error = objectMapper.writeValueAsString(insufficientProductStockList);
      } catch (Exception e) {
        LOGGER.error("Could not serialize product list", e);
      }
      throw new InsufficientProductStockException(error);
    }
  }
}
