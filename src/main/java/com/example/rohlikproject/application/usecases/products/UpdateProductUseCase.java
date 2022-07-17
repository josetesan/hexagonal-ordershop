package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.application.command.product.UpdateProductCommand;
import com.example.rohlikproject.domain.model.product.Product;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductUseCase {

  private final ProductRepository productRepository;

  public UpdateProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void handle(UpdateProductCommand updateProductCommand) {
    var product =
        new Product(
            updateProductCommand.getProductId(),
            updateProductCommand.getAmount(),
            updateProductCommand.getPricePerUnit(),
            updateProductCommand.getName());
    this.productRepository.updateProduct(product);
  }
}
