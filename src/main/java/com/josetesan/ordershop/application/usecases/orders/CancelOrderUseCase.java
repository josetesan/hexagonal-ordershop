package com.josetesan.ordershop.application.usecases.orders;

import com.josetesan.ordershop.application.usecases.products.ProductRepository;
import com.josetesan.ordershop.domain.model.order.OrderStatus;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.exceptions.OrderNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CancelOrderUseCase {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderUseCase.class);

  public CancelOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(UUID orderId) throws OrderNotFoundException {

    var order =
        this.orderRepository
            .findOrder(orderId)
            .filter(theOrder -> theOrder.getStatus().equals(OrderStatus.OPEN))
            .orElseThrow(() -> new OrderNotFoundException(orderId));

    this.orderRepository.cancelOrder(orderId);

    // return all previous products

    order
        .getItems()
        .forEach(
            item ->
                this.productRepository
                    .findProduct(item.getProductId())
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
    LOGGER.info("Order {} canceled, products returned", orderId);
  }
}
