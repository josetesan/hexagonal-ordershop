package com.josetesan.ordershop.application.query.product;

import com.josetesan.ordershop.application.querybus.QueryHandler;
import com.josetesan.ordershop.application.usecases.products.GetProductUseCase;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetProductQueryHandler implements QueryHandler<Product, GetProductQuery> {

  private final GetProductUseCase useCase;

  public GetProductQueryHandler(GetProductUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public Product handle(GetProductQuery query) throws ProductNotFoundException {
    return useCase.handle(query);
  }
}
