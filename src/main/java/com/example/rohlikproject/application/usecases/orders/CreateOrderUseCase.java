package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.example.rohlikproject.application.usecases.products.ProductRepository;
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
    System.out.println(command);
    /*
    var aProduct =
        this.productRepository
            .retrieveProductWithAmountBiggerThan(command.getProductId(), command.getAmount())
            .orElseThrow(
                () ->
                    new InvalidParameterException(
                        String.format(
                            "Product %s does not have %d items available",
                            command.getProductId(), command.getAmount())));

    var newProduct =
        new Product(
            aProduct.getId(),
            aProduct.getAmount() - command.getAmount(),
            aProduct.getUnitPrice(),
            aProduct.getName());
    productRepository.updateProduct(newProduct);

    var order = new Order(aProduct, command.getAmount());
    orderRepository.createOrder(order);
    */
  }
}
