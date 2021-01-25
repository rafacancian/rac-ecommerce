package com.ecommerce.applicationmanager.models;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED,
    DELIVERED,
    CANCELED;
}
