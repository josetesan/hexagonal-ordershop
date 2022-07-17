package com.example.rohlikproject;

import com.example.rohlikproject.application.command.order.CreateOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrders {

  public static final String JSON =
      """
            [
                {
                    "amount": "3",
                    "productId": "c3251597-ab43-402d-bc7d-ba0991f5a3da"
                },
                {
                    "amount": "2",
                    "productId": "2c4c4b5e-b0a4-4350-8c2d-96c8358992e9"
                }
            ]

            """;

  @Test
  @JsonDeserialize
  public void testJson() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType javaType =
        objectMapper.getTypeFactory().constructCollectionType(List.class, CreateOrder.class);
    var list = objectMapper.readValue(JSON, javaType);
    Assertions.assertThat(list).isInstanceOf(List.class);
  }
}
