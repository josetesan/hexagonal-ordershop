package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.domain.model.product.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** */
public interface ProductRepository {

  /**
   * @param product
   */
  void createProduct(Product product);

  /**
   * @return
   */
  List<Product> retrieveProducts();

  /**
   * @param id
   * @return
   */
  Optional<Product> retrieveProductWithAmountBiggerThan(UUID id, Integer amount);

  /**
   * @param product
   */
  void updateProduct(Product product);

  Optional<Product> findByIdForUpdate(UUID id);
}
