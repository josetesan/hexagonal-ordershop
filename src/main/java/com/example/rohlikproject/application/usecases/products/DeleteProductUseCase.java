package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.application.command.product.DeleteProductCommand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteProductUseCase {

  ProductRepository productRepository;

  public DeleteProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(DeleteProductCommand command) {
    this.productRepository.deleteProduct(command.getProductId());
  }
}
