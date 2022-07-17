package com.example.rohlikproject.application.query.order;

import com.example.rohlikproject.application.querybus.QueryHandler;
import com.example.rohlikproject.application.usecases.orders.GetOrdersUseCase;
import com.example.rohlikproject.domain.model.order.Order;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetOrdersDueQueryHandler implements QueryHandler<List<Order>, GetOrdersDueQuery> {

  private final GetOrdersUseCase useCase;

  public GetOrdersDueQueryHandler(GetOrdersUseCase useCase) {
    this.useCase = useCase;
  }

  @Override
  public List<Order> handle(GetOrdersDueQuery query) throws Exception {
    return useCase.handle(query.getMaxTimeDue());
  }
}
