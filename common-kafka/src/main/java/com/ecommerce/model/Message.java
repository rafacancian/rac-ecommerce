package com.ecommerce.model;

import lombok.Data;

@Data
public class Message<T> {

    private Correlate correlate;
    private T payload;

    public Message(Correlate correlate, T payload) {
        this.correlate = correlate;
        this.payload = payload;
    }
}
