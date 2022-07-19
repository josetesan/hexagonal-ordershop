package com.josetesan.ordershop.application.query.order;

import com.josetesan.ordershop.application.querybus.QueryHandler;
import com.josetesan.ordershop.application.usecases.orders.GetOrdersUseCase;
import com.josetesan.ordershop.domain.model.order.Order;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetOrdersQueryHandler implements QueryHandler<List<Order>, GetOrdersQuery> {

  private final GetOrdersUseCase useCase;

  public GetOrdersQueryHandler(GetOrdersUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public List<Order> handle(GetOrdersQuery query) throws Exception {
    return useCase.handle();
  }
}
