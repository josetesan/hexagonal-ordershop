package com.josetesan.ordershop.application.usecases.products;

import com.josetesan.ordershop.application.query.product.GetProductQuery;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
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
