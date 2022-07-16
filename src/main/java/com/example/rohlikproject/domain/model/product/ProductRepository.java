package com.example.rohlikproject.domain.model.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, UUID> {

  @Modifying
  @Query("UPDATE products SET name = :name, amount = :amount, price = :price  WHERE id = :id")
  boolean update(
      @Param("id") UUID id,
      @Param("name") String name,
      @Param("amount") Integer amount,
      @Param("price") double price);

  @Override
  List<Product> findAll();

  @Lock(LockMode.PESSIMISTIC_READ)
  Optional<Product> findByIdAndAmountIsGreaterThanEqual(UUID id, Integer amount);
}
