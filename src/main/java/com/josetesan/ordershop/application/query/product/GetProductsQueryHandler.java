package com.josetesan.ordershop.application.query.product;

import com.josetesan.ordershop.application.querybus.QueryHandler;
import com.josetesan.ordershop.application.usecases.products.GetProductsUseCase;
import com.josetesan.ordershop.domain.model.product.Product;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
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
