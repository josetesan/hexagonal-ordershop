package com.josetesan.ordershop.infrastructure.repository.database;

import com.josetesan.ordershop.application.usecases.products.ProductRepository;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.mapping.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Component;

@Component
@Adapter
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

  public Optional<Product> findProduct(UUID id) {
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
  @Lock(LockMode.PESSIMISTIC_WRITE)
  public void deleteProduct(UUID productId) {
    findProduct(productId)
        .ifPresent(
            product ->
                productRepository.update(productId, product.getName(), 0, product.getUnitPrice()));
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
  @Lock(LockMode.PESSIMISTIC_WRITE)
  public void updateProduct(Product product) {
    ProductEntity productEntity =
        new ProductEntity(
            product.getId(), product.getAmount(), product.getUnitPrice(), product.getName());
    productRepository.update(
        productEntity.getId(), product.getName(), product.getAmount(), product.getUnitPrice());
  }
}
