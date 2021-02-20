package com.ecommerce.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Correlate {

    private String id;
    private String name;

    public Correlate(String name) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.name = name;
    }
}
