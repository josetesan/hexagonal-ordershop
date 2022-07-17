package com.example.rohlikproject.infrastructure.rest.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.rohlikproject.infrastructure.rest.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {

    this.mockMvc
        .perform(get("/v1/products"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("")))
        .andDo(document("products"));
  }
}
