package com.ecommerce.kafka.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class Order implements Serializable {
    private String code;
    private String name;
    private String description;
    private OrderStatus orderStatus;
    private LocalDate creationDate;
    private Double price;
    private User user;
}
