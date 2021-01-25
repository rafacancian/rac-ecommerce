package com.ecommerce.frauddetector.models;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED,
    DELIVERED,
    CANCELED;
}
