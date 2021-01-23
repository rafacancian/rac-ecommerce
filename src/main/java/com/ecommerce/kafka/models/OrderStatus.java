package com.ecommerce.kafka.models;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    DELIVERED,
    CANCELED;
}
