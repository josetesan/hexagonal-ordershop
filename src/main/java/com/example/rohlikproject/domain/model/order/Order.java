package com.example.rohlikproject.domain.model.order;

import com.example.rohlikproject.application.usecases.orders.ProductRequestDto;
import com.example.rohlikproject.domain.model.product.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order implements Serializable {

  private UUID id;
  private Instant createDate;
  private Instant closedDate;
  private OrderStatus status;
  private Set<OrderItem> items;

  public Order(
      UUID id, Instant createDate, Instant closedDate, OrderStatus status, Set<OrderItem> items) {
    this.id = id;
    this.createDate = createDate;
    this.closedDate = closedDate;
    this.status = status;
    this.items = items;
  }

  public Order(List<ProductRequestDto> requestList) {
    this.createDate = Instant.now();
    this.status = OrderStatus.OPEN;
    this.items =
        requestList.stream()
            .map(
                request -> {
                  var product =
                      new Product(
                          request.productId(),
                          request.amountRequested(),
                          request.pricePerUnit(),
                          request.name());
                  return new OrderItem(product, request.amountRequested());
                })
            .collect(Collectors.toSet());
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public Instant getClosedDate() {
    return closedDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public UUID getId() {
    return id;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public Double getTotalPrice() {
    var total = 0.0d;
    for (OrderItem item : items) {
      total += item.getUnitPrice() * item.getAmount();
    }
    return total;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Order{");
    sb.append("id=").append(id);
    sb.append(", createDate=").append(createDate);
    sb.append(", closedDate=").append(closedDate);
    sb.append(", status=").append(status);
    sb.append(", items=").append(items);
    sb.append(", totalPrice=").append(getTotalPrice());
    sb.append('}');
    return sb.toString();
  }
}
