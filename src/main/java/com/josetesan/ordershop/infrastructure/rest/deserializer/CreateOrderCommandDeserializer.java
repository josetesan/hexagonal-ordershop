package com.josetesan.ordershop.infrastructure.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.josetesan.ordershop.application.command.order.CreateOrder;
import com.josetesan.ordershop.application.command.order.CreateOrderCommand;
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
