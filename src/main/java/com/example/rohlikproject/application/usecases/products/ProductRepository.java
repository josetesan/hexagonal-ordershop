package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.domain.model.product.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jmolecules.architecture.hexagonal.Adapter;

@Adapter
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
   * @param product
   */
  void updateProduct(Product product);

  /**
   * @param id
   * @return
   */
  Optional<Product> findByIdForUpdate(UUID id);

  /**
   * @param productId
   */
  void deleteProduct(UUID productId);
}
