package com.example.rohlikproject.application.usecases.orders;

import com.example.rohlikproject.domain.model.order.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.jmolecules.architecture.hexagonal.Port;

@Port
public interface OrderRepository {

  public Optional<Order> findOrder(UUID order);

  public void createOrder(Order order);

  public void cancelOrder(UUID order);

  public void closeOrder(UUID order);

  List<Order> findAll();

  List<Order> findOrdersDue(Integer maxTimeOrdersDue);
}
