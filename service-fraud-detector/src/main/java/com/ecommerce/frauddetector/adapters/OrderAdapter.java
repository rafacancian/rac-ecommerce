package com.ecommerce.frauddetector.adapters;

import com.ecommerce.frauddetector.models.Order;
import com.ecommerce.frauddetector.models.OrderStatus;

import java.time.LocalDate;
import java.util.UUID;

public class OrderAdapter {

    public static Order createMock() {
        return Order.builder()
                .code(UUID.randomUUID().toString())
                .name("order01")
                .description("Description order01")
                .orderStatus(OrderStatus.CREATED)
                .creationDate(LocalDate.now())
                .price(123.99)
                .user("rafa cancian")
                .build();

    }
}
