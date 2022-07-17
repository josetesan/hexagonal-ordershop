package com.example.rohlikproject.application.query.order;

import com.example.rohlikproject.application.querybus.Query;
import com.example.rohlikproject.domain.model.order.Order;
import java.util.List;

public class GetOrdersDueQuery extends Query<List<Order>> {

  private int maxTimeDue;

  public GetOrdersDueQuery(int maxTimeDue) {
    this.maxTimeDue = maxTimeDue;
  }

  public int getMaxTimeDue() {
    return maxTimeDue;
  }
}
