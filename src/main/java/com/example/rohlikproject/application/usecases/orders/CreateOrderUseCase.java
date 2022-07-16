package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.domain.model.order.OrderRepository;
import com.example.rohlikproject.domain.model.product.ProductRepository;
import java.security.InvalidParameterException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  public CreateOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(CreateOrderCommand command) {

    var aProduct =
        this.productRepository
            .findByIdAndAmountIsGreaterThanEqual(command.getProductId(), command.getAmount())
            .orElseThrow(
                () ->
                    new InvalidParameterException(
                        String.format(
                            "Product %s does not have %d items available",
                            command.getProductId(), command.getAmount())));

    orderRepository.save(new Order(aProduct, command.getAmount()));
    productRepository.update(
        aProduct.getId(),
        aProduct.getName(),
        aProduct.getAmount() - command.getAmount(),
        aProduct.getPrice());

    orderRepository.findAll().forEach(System.out::println);
  }
}
