package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Order implements Serializable {

    private String code;
    private String name;
    private String description;
    private OrderStatus orderStatus;
    private LocalDate creationDate;
    private Double price;
    private String email;
    private User user;
}
