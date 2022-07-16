package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.domain.model.product.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetProductsUseCase {

  private final ProductRepository productRepository;

  public GetProductsUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> handle() {
    return productRepository.findAll();
  }
}
