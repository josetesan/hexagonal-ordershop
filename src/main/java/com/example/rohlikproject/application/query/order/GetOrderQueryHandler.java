package com.example.rohlikproject.application.query.order;

import com.example.rohlikproject.application.querybus.QueryHandler;
import com.example.rohlikproject.application.usecases.orders.GetOrderUseCase;
import com.example.rohlikproject.domain.model.order.Order;
import org.springframework.stereotype.Component;

@Component
public class GetOrderQueryHandler implements QueryHandler<Order, GetOrderQuery> {

  private final GetOrderUseCase useCase;

  public GetOrderQueryHandler(GetOrderUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public Order handle(GetOrderQuery query) throws Exception {
    return useCase.handle(query);
  }
}
