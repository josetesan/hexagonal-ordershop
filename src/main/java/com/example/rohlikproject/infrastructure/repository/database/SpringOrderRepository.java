package com.example.rohlikproject.infrastructure.repository.database;

import com.example.rohlikproject.domain.model.order.OrderStatus;
import com.example.rohlikproject.infrastructure.mapping.OrderEntity;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpringOrderRepository extends CrudRepository<OrderEntity, UUID> {
  @Modifying
  @Query("UPDATE orders set status = :status, closed_date = :closed_date where id = :id")
  boolean update(
      @Param("id") UUID id,
      @Param("status") OrderStatus status,
      @Param("closed_date") Instant closed_date);

  @Override
  List<OrderEntity> findAll();

  List<OrderEntity> findAllByStatusEqualsAndCreateDateBefore(
      OrderStatus status, Instant creationTime);


}
