package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.application.query.product.GetProductQuery;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetProductUseCase {

  private final ProductRepository productRepository;

  public GetProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product handle(GetProductQuery query) throws ProductNotFoundException {
    return this.productRepository
        .findProduct(query.getId())
        .orElseThrow(() -> new ProductNotFoundException(query.getId()));
  }
}
