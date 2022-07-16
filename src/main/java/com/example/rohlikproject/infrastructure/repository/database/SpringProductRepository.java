package com.example.rohlikproject.infrastructure.repository.database;

import com.example.rohlikproject.infrastructure.mapping.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpringProductRepository extends CrudRepository<ProductEntity, UUID> {

  @Modifying
  @Query("UPDATE products SET name = :name, amount = :amount, price = :price  WHERE id = :id")
  boolean update(
      @Param("id") UUID id,
      @Param("name") String name,
      @Param("amount") Integer amount,
      @Param("price") double price);

  @Override
  List<ProductEntity> findAll();

  @Lock(LockMode.PESSIMISTIC_READ)
  Optional<ProductEntity> findByIdAndAmountIsGreaterThanEqual(UUID id, Integer amount);
}
