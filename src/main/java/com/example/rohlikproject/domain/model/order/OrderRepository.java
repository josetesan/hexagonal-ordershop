package com.example.rohlikproject.domain.model.order;

import java.time.Instant;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Long> {
  @Modifying
  @Query("UPDATE orders set status = :status, closed_date = :closed_date where id = :id")
  boolean update(
      @Param("id") Long id,
      @Param("status") Integer status,
      @Param("closed_date") Instant closed_date);
}
