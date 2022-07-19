package com.josetesan.ordershop.application.usecases.products;

import com.josetesan.ordershop.domain.model.product.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
