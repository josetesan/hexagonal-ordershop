package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.domain.model.product.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.jmolecules.architecture.hexagonal.Port;

@Port
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
   * @param productId
   */
  void deleteProduct(UUID productId);

  Optional<Product> findProduct(UUID id);
}
