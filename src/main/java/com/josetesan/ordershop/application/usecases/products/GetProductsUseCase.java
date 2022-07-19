package com.josetesan.ordershop.application.usecases.products;

import com.josetesan.ordershop.domain.model.product.Product;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetProductsUseCase {

  private final ProductRepository productRepository;

  public GetProductsUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> handle() {
    return productRepository.retrieveProducts();
  }
}
