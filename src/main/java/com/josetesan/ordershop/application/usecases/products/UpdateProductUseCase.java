package com.josetesan.ordershop.application.usecases.products;

import com.josetesan.ordershop.application.command.product.UpdateProductCommand;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
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
