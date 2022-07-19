package com.josetesan.ordershop.application.usecases.products;

import com.josetesan.ordershop.application.command.product.DeleteProductCommand;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteProductUseCase {

  ProductRepository productRepository;

  public DeleteProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(DeleteProductCommand command) throws ProductNotFoundException {
    if (productRepository.findProduct(command.getProductId()).isPresent()) {
      this.productRepository.deleteProduct(command.getProductId());
    } else throw new ProductNotFoundException(command.getProductId());
  }
}
