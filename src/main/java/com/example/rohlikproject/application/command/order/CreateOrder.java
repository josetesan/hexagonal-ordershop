package com.example.rohlikproject.application.command.order;

import java.util.UUID;

public record CreateOrder(UUID productId, Integer amount) {}
