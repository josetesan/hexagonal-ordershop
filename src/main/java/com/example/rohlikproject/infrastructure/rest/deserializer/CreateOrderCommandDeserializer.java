package com.example.rohlikproject.infrastructure.rest.deserializer;

import com.example.rohlikproject.application.command.order.CreateOrder;
import com.example.rohlikproject.application.command.order.CreateOrderCommand;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CreateOrderCommandDeserializer extends JsonDeserializer<CreateOrderCommand> {

  @Override
  public CreateOrderCommand deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    var javaType = ctxt.getTypeFactory().constructCollectionType(List.class, CreateOrder.class);
    return new CreateOrderCommand(p.getCodec().readValue(p, javaType));
  }
}
