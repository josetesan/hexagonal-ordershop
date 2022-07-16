package com.example.rohlikproject.infrastructure.repository.database;

import com.example.rohlikproject.application.usecases.orders.OrderRepository;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.domain.model.order.OrderItem;
import com.example.rohlikproject.domain.model.order.OrderStatus;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.mapping.OrderEntity;
import com.example.rohlikproject.infrastructure.mapping.OrderItemEntity;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

  private SpringOrderRepository orderRepository;

  public OrderRepositoryAdapter(SpringOrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Optional<Order> findOrder(UUID orderId) {
    return orderRepository
        .findById(orderId)
        .map(
            orderEntity -> {
              var orderItems =
                  orderEntity.getItems().stream()
                      .map(
                          item -> {
                            var product =
                                new Product(
                                    item.getProductId(), item.getAmount(), item.getUnitPrice(), "");
                            return new OrderItem(product, item.getAmount());
                          })
                      .collect(Collectors.toSet());

              return new Order(
                  orderEntity.getId(),
                  orderEntity.getCreateDate(),
                  orderEntity.getClosedDate(),
                  orderEntity.getStatus(),
                  orderItems);
            });
  }

  @Override
  public void createOrder(Order order) {
    var orderItems =
        order.getItems().stream()
            .map(
                item ->
                    new OrderItemEntity(item.getProductId(), item.getAmount(), item.getUnitPrice()))
            .collect(Collectors.toSet());

    var entity =
        new OrderEntity(
            order.getCreateDate(), order.getClosedDate(), order.getStatus(), orderItems);
    this.orderRepository.save(entity);
  }

  @Override
  public void cancelOrder(UUID orderId) {

    this.orderRepository.update(orderId, OrderStatus.CANCELED, Instant.now());
  }

  @Override
  public void closeOrder(UUID orderId) {
    this.orderRepository.update(orderId, OrderStatus.CLOSED, Instant.now());
  }

  @Override
  public List<Order> findAll() {
    return orderRepository.findAll().stream()
        .map(
            orderEntity -> {
              var items =
                  orderEntity.getItems().stream()
                      .map(
                          item -> {
                            var product =
                                new Product(
                                    item.getProductId(), item.getAmount(), item.getUnitPrice(), "");
                            return new OrderItem(product, item.getAmount());
                          })
                      .collect(Collectors.toSet());
              return new Order(
                  orderEntity.getId(),
                  orderEntity.getCreateDate(),
                  orderEntity.getClosedDate(),
                  orderEntity.getStatus(),
                  items);
            })
        .toList();
  }
}
