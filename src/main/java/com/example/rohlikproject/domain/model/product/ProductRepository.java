package com.example.rohlikproject.domain.model.product;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

  @Modifying
  @Query("UPDATE products SET name = :name, amount = :amount, price = :price  WHERE id = :id")
  boolean update(
      @Param("id") Long id,
      @Param("name") String name,
      @Param("ammount") Integer amount,
      @Param("price") double price);

  @Override
  List<Product> findAll();
}
