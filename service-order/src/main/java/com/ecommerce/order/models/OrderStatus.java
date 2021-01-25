package com.ecommerce.order.models;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED,
    DELIVERED,
    CANCELED;
}
