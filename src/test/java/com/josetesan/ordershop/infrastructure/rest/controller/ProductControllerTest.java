package com.josetesan.ordershop.infrastructure.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josetesan.ordershop.application.commandbus.CommandBus;
import com.josetesan.ordershop.application.querybus.QueryBus;
import com.josetesan.ordershop.domain.model.product.Product;
import com.josetesan.ordershop.infrastructure.rest.controllers.ProductController;
import com.josetesan.ordershop.infrastructure.rest.exceptions.ProductNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private CommandBus commandBus;
  @MockBean private QueryBus queryBus;

  @Test
  @DisplayName("Returns a product when requested")
  public void shouldReturnProductWhenFound() throws Exception {

    var uid = UUID.randomUUID();

    Product product = new Product(uid, 200, 200.0d, "name");
    when(queryBus.handle(ArgumentMatchers.any())).thenReturn(product);

    this.mockMvc
        .perform(get("/v1/products/{id}", uid.toString()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(product)))
        .andDo(document("product/find/success"));
  }

  @Test
  @DisplayName("Returns an error when requested product not found")
  public void shouldReturnErrorWhenNotFound() throws Exception {

    var uid = UUID.randomUUID();

    when(queryBus.handle(ArgumentMatchers.any())).thenThrow(new ProductNotFoundException(uid));

    this.mockMvc
        .perform(get("/v1/products/{id}", uid.toString()))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andDo(document("product/find/error"));
  }
}
