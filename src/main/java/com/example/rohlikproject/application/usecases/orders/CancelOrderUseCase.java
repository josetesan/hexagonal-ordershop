package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.application.command.order.CancelOrderCommand;
import com.example.rohlikproject.application.usecases.products.ProductRepository;
import com.example.rohlikproject.domain.model.order.OrderStatus;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CancelOrderUseCase {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  public CancelOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(CancelOrderCommand command) throws OrderNotFoundException {

    var order =
        this.orderRepository
            .findOrder(command.getOrderId())
            .filter(theOrder -> theOrder.getStatus().equals(OrderStatus.OPEN))
            .orElseThrow(() -> new OrderNotFoundException(command.getOrderId()));

    this.orderRepository.cancelOrder(command.getOrderId());

    // return all previous products

    order
        .getItems()
        .forEach(
            item ->
                this.productRepository
                    .findByid(item.getProductId())
                    .map(
                        product -> {
                          var aProduct =
                              new Product(
                                  product.getId(),
                                  product.getAmount() + item.getAmount(),
                                  product.getUnitPrice(),
                                  product.getName());
                          this.productRepository.updateProduct(aProduct);
                          return Optional.empty();
                        }));
  }
}
