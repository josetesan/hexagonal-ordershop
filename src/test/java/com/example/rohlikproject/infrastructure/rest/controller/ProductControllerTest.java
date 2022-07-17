package com.example.rohlikproject.infrastructure.rest.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.rohlikproject.application.commandbus.CommandBus;
import com.example.rohlikproject.application.querybus.QueryBus;
import com.example.rohlikproject.domain.model.product.Product;
import com.example.rohlikproject.infrastructure.rest.controllers.ProductController;
import java.util.List;
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

  @MockBean private CommandBus commandBus;

  @MockBean private QueryBus queryBus;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {

    Product product = mock(Product.class);
    when(queryBus.handle(ArgumentMatchers.any())).thenReturn(List.of(product));

    this.mockMvc
        .perform(get("/v1/products"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("")))
        .andDo(document("products"));
  }
}
