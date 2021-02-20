package com.ecommerce.model;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED,
    DELIVERED,
    CANCELED;
}
