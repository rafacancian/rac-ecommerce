package com.ecommerce.kafka.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Order {
    private String code;
    private String name;
    private String description;
    private OrderStatus orderStatus;
    private LocalDate creationDate;
    private Double price;
    private User user;
}
