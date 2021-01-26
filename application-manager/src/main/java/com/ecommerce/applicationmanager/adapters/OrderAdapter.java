package com.ecommerce.applicationmanager.adapters;


import com.ecommerce.applicationmanager.models.Order;
import com.ecommerce.applicationmanager.models.OrderStatus;
import com.ecommerce.applicationmanager.utils.GenerateRandomNumber;

import java.time.LocalDate;
import java.util.UUID;

public class OrderAdapter {

    public static Order createMock() {
        final String id = UUID.randomUUID().toString();
        return Order.builder()
                .code(id)
                .name("order-" + id)
                .description("Description order-" + id)
                .orderStatus(OrderStatus.CREATED)
                .creationDate(LocalDate.now())
                .price(GenerateRandomNumber.execute(50, 400))
                .user(UserAdater.createMock())
                .build();
    }
}
