package com.josetesan.ordershop.application.usecases.products;

import static org.mockito.Mockito.*;

import com.josetesan.ordershop.application.command.product.CreateProductCommand;
import com.josetesan.ordershop.domain.model.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateProductUseCaseTest {

  private ProductRepository productRepository;

  @Test
  @DisplayName("Creates a product")
  public void canCreateAProduct() {
    var product = new Product(null, 1, 200.0d, "name");
    productRepository = mock(ProductRepository.class);
    doNothing().when(productRepository).createProduct(product);
    CreateProductUseCase createProductUseCase = new CreateProductUseCase(productRepository);
    CreateProductCommand createProductCommand = new CreateProductCommand("name", 200d, 1);
    createProductUseCase.handle(createProductCommand);
    verify(productRepository, times(1)).createProduct(product);
  }
}
