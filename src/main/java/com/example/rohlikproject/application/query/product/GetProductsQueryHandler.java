package com.example.rohlikproject.application.query.product;

import com.example.rohlikproject.application.querybus.QueryHandler;
import com.example.rohlikproject.application.usecases.products.GetProductsUseCase;
import com.example.rohlikproject.domain.model.product.Product;
import java.util.List;

public class GetProductsQueryHandler implements QueryHandler<List<Product>, GetProductsQuery> {

  private final GetProductsUseCase useCase;

  public GetProductsQueryHandler(GetProductsUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public List<Product> handle(GetProductsQuery query) throws Exception {
    return useCase.handle();
  }
}
