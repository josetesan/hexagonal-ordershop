package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.application.command.product.UpdateProductCommand;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateProductUseCase {

  private final ProductRepository productRepository;

  public UpdateProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public void handle(UpdateProductCommand updateProductCommand) throws ProductNotFoundException {
    var product =
        new Product(
            updateProductCommand.getProductId(),
            updateProductCommand.getAmount(),
            updateProductCommand.getPricePerUnit(),
            updateProductCommand.getName());
    if (productRepository.findProduct(updateProductCommand.getProductId()).isPresent()) {
      this.productRepository.updateProduct(product);
    } else throw new ProductNotFoundException(updateProductCommand.getProductId());
  }
}
