package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.CreateOrder;
import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.application.usecases.products.ProductRepository;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.exceptions.InsufficientProductStockException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderUseCase.class);

  public CreateOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(CreateOrderCommand command) throws InsufficientProductStockException {

    var insufficientProductStockList = new ArrayList<CreateOrder>();

    var requestList =
        command.getCreateOrder().stream()
            .map(
                createOrder ->
                    this.productRepository
                        .findByIdForUpdate(createOrder.productId())
                        .map(
                            stock ->
                                updateProductStock(
                                    insufficientProductStockList, createOrder, stock))
                        .orElseGet(
                            () -> {
                              insufficientProductStockList.add(createOrder);
                              return null;
                            }))
            .filter(Objects::isNull)
            .toList();

    if (!insufficientProductStockList.isEmpty()) {
      throw new InsufficientProductStockException(insufficientProductStockList);
    } else {
      orderRepository.createOrder(new Order(requestList));
    }
  }

  private ProductRequestDto updateProductStock(
      List<CreateOrder> insufficientProductStockList, CreateOrder createOrder, Product stock) {
    if (stock.getAmount() >= createOrder.amount()) {
      var newProduct =
          new Product(
              stock.getId(),
              stock.getAmount() - createOrder.amount(),
              stock.getUnitPrice(),
              stock.getName());
      productRepository.updateProduct(newProduct);
      return new ProductRequestDto(
          stock.getId(), stock.getUnitPrice(), createOrder.amount(), stock.getName());
    } else {
      var notEnough =
          new CreateOrder(createOrder.productId(), createOrder.amount() - stock.getAmount());
      insufficientProductStockList.add(notEnough);
      return null;
    }
  }
}
