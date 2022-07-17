package com.example.rohlikproject.application.usecases.orders;

import java.util.UUID;

public record ProductRequestDto(
    UUID productId,
    Double pricePerUnit,
    Integer amountRequested,
    Integer amountInStock,
    String name) {}
