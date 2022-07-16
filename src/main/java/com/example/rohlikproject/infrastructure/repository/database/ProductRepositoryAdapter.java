package com.example.rohlikproject.infrastructure.repository.database;

import com.example.rohlikproject.application.usecases.products.ProductRepository;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.mapping.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

  SpringProductRepository productRepository;

  public ProductRepositoryAdapter(SpringProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void createProduct(Product product) {

    ProductEntity productEntity =
        new ProductEntity(
            product.getId(), product.getAmount(), product.getUnitPrice(), product.getName());
    productRepository.save(productEntity);
  }

  @Override
  public Optional<Product> findByid(UUID id) {
    return productRepository
        .findById(id)
        .map(
            productEntity ->
                new Product(
                    productEntity.getId(),
                    productEntity.getAmount(),
                    productEntity.getPrice(),
                    productEntity.getName()));
  }

  @Override
  public List<Product> retrieveProducts() {
    return productRepository.findAll().stream()
        .map(
            productEntity ->
                new Product(
                    productEntity.getId(),
                    productEntity.getAmount(),
                    productEntity.getPrice(),
                    productEntity.getName()))
        .toList();
  }

  @Override
  public Optional<Product> retrieveProductWithAmountBiggerThan(UUID id, Integer amount) {
    return productRepository
        .findByIdAndAmountIsGreaterThanEqual(id, amount)
        .map(
            productEntity ->
                new Product(
                    productEntity.getId(),
                    productEntity.getAmount(),
                    productEntity.getPrice(),
                    productEntity.getName()));
  }

  @Override
  public void updateProduct(Product product) {
    ProductEntity productEntity =
        new ProductEntity(
            product.getId(), product.getAmount(), product.getUnitPrice(), product.getName());
    productRepository.update(
        productEntity.getId(), product.getName(), product.getAmount(), product.getUnitPrice());
  }
}
