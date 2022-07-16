package com.example.rohlikproject.application.usecases.products;

import com.example.rohlikproject.application.command.product.CreateProductCommand;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.domain.model.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateProductUseCase {

  private final ProductRepository productRepository;

  public CreateProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void handle(CreateProductCommand command) {
    Product product = new Product(command.amount(), command.price(), command.name());
    this.productRepository.save(product);
  }
}
