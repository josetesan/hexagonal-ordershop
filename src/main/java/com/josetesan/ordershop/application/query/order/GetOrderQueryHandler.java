package com.josetesan.ordershop.application.query.order;

import com.josetesan.ordershop.application.querybus.QueryHandler;
import com.josetesan.ordershop.application.usecases.orders.GetOrderUseCase;
import com.josetesan.ordershop.domain.model.order.Order;
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
