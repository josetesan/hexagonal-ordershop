package com.example.rohlikproject.infrastructure.rest.controllers;

import com.example.rohlikproject.application.command.product.CreateProductCommand;
import com.example.rohlikproject.application.command.product.DeleteProductCommand;
import com.example.rohlikproject.application.command.product.UpdateProductCommand;
import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.query.product.GetProductsQuery;
import com.example.rohlikproject.application.querybus.QueryBus;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.Constants;
import com.example.rohlikproject.infrastructure.rest.exceptions.ProductNotFoundException;
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

  @GetMapping
  @ResponseBody
  public ResponseEntity<List> getProducts() throws Exception {
    GetProductsQuery query = new GetProductsQuery();
    List<Product> products = this.queryBus.handle(query);
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
