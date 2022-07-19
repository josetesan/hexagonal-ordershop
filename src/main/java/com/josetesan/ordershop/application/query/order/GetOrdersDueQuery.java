package com.josetesan.ordershop.application.query.order;

import com.josetesan.ordershop.application.querybus.Query;
import com.josetesan.ordershop.domain.model.order.Order;
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
