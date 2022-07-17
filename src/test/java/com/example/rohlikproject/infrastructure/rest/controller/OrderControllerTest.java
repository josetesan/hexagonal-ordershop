package com.example.rohlikproject.infrastructure.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.querybus.QueryBus;
import com.example.rohlikproject.domain.model.order.Order;
import com.example.rohlikproject.domain.model.order.OrderItem;
import com.example.rohlikproject.domain.model.order.OrderStatus;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.controllers.OrderController;
import com.example.rohlikproject.infrastructure.rest.exceptions.OrderNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private CommandBus commandBus;
  @MockBean private QueryBus queryBus;

  @Test
  @DisplayName("Returns an Order when requested")
  public void shouldReturnOrderWhenFound() throws Exception {

    var uid = UUID.randomUUID();

    var product = new Product(uid, 200, 200.0d, "name");
    var orderItem = new OrderItem(product, 1);
    var order = new Order(uid, Instant.now(), null, OrderStatus.OPEN, Set.of(orderItem));

    when(queryBus.handle(ArgumentMatchers.any())).thenReturn(order);

    FieldDescriptor[] items =
        new FieldDescriptor[] {
          fieldWithPath("productId").description("The ProductId purchased").type(UUID.class),
          fieldWithPath("amount")
              .description("The amount of productId requested")
              .type(Integer.class),
          fieldWithPath("unitPrice")
              .description("Unitary price of the product purchased")
              .type(Double.class),
          fieldWithPath("totalPrice")
              .description("Total price of the product request")
              .type(Double.class)
        };

    this.mockMvc
        .perform(get("/v1/orders/{id}", uid.toString()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(order)))
        .andDo(
            document(
                "orders/find/success",
                responseFields(
                        fieldWithPath("id")
                            .description("Order id, created randomly by DDBB")
                            .type(UUID.class),
                        fieldWithPath("createDate")
                            .description("The Order create date, as stored in database")
                            .type(Timestamp.class),
                        fieldWithPath("closedDate")
                            .description("The order closedDate, wether is canceled or paid")
                            .type(Timestamp.class),
                        fieldWithPath("status")
                            .description(
                                "The order status, OPEN as just created, CANCELED or CLOSED")
                            .type(OrderStatus.class),
                        fieldWithPath("totalPrice")
                            .description("The total amount of money due to pay")
                            .type(Double.class),
                        fieldWithPath("items")
                            .description("The line items of the order")
                            .type(OrderItem.class),
                        fieldWithPath("items[]")
                            .description("An Array of OrderItems")
                            .type(List.class))
                    .andWithPrefix("items[].", items)));
  }

  @Test
  @DisplayName("Returns a not found when order requested is not found")
  public void shouldReturnErrorWhenNotFound() throws Exception {

    var uid = UUID.randomUUID();

    when(queryBus.handle(ArgumentMatchers.any())).thenThrow(new OrderNotFoundException(uid));

    this.mockMvc
        .perform(get("/v1/orders/{id}", uid.toString()))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andDo(document("orders/find/error"));
  }
}
