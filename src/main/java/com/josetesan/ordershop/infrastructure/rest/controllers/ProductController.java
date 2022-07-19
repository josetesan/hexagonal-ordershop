package com.josetesan.ordershop.infrastructure.rest.controllers;

import com.josetesan.ordershop.application.command.product.CreateProductCommand;
import com.josetesan.ordershop.application.command.product.DeleteProductCommand;
import com.josetesan.ordershop.application.command.product.UpdateProductCommand;
import com.josetesan.ordershop.application.commandbus.CommandBus;
import com.josetesan.ordershop.application.query.product.GetProductQuery;
import com.josetesan.ordershop.application.query.product.GetProductsQuery;
import com.josetesan.ordershop.application.querybus.QueryBus;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.Constants;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

  private final CommandBus commandBus;
  private final QueryBus queryBus;

  public ProductController(CommandBus commandBus, QueryBus queryBus) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping
  public ResponseEntity<String> createProduct(@RequestBody Product product) throws Exception {
    CreateProductCommand createProductCommand =
        new CreateProductCommand(product.getName(), product.getUnitPrice(), product.getAmount());
    this.commandBus.handle(createProductCommand);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) throws Exception {
    GetProductQuery getProductQuery = new GetProductQuery(id);
    try {
      Product product = this.queryBus.handle(getProductQuery);
      return ResponseEntity.ok(product);
    } catch (ProductNotFoundException pnfe) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List> getProducts() throws Exception {
    List<Product> products = this.queryBus.handle(new GetProductsQuery());
    return ResponseEntity.ok().body(products);
  }

  @PutMapping()
  public ResponseEntity<String> updateProduct(@RequestBody Product product) throws Exception {
    UpdateProductCommand updateProductCommand =
        new UpdateProductCommand(
            product.getId(), product.getName(), product.getUnitPrice(), product.getAmount());
    try {
      this.commandBus.handle(updateProductCommand);
    } catch (ProductNotFoundException pnfe) {
      return ResponseEntity.badRequest()
          .headers(httpHeaders -> httpHeaders.add(Constants.X_ERROR_HEADER, pnfe.getMessage()))
          .build();
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID productId) throws Exception {
    DeleteProductCommand deleteProductCommand = new DeleteProductCommand(productId);
    try {
      this.commandBus.handle(deleteProductCommand);
    } catch (ProductNotFoundException pnfe) {
      return ResponseEntity.badRequest()
          .headers(httpHeaders -> httpHeaders.add(Constants.X_ERROR_HEADER, pnfe.getMessage()))
          .build();
    }
    return ResponseEntity.noContent().build();
  }
}
