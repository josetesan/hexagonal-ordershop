package com.example.rohlikproject.application.query.product;

import com.example.rohlikproject.application.querybus.Query;
import com.example.rohlikproject.domain.model.product.Product;
import java.util.UUID;

public class GetProductQuery extends Query<Product> {

  private UUID id;

  public GetProductQuery(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
