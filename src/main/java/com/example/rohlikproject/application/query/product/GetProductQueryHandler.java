package com.example.rohlikproject.application.query.product;

import com.example.rohlikproject.application.querybus.QueryHandler;
import com.example.rohlikproject.application.usecases.products.GetProductUseCase;
import com.example.rohlikproject.domain.model.product.Product;
import org.springframework.stereotype.Component;

@Component
public class GetProductQueryHandler implements QueryHandler<Product, GetProductQuery> {

  private final GetProductUseCase useCase;

  public GetProductQueryHandler(GetProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public Product handle(GetProductQuery query) throws Exception {
    return useCase.handle(query);
  }
}
