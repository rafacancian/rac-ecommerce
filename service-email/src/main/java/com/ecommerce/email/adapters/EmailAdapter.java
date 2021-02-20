package com.ecommerce.email.adapters;

import com.ecommerce.model.Email;

import java.util.UUID;

public class EmailAdapter {

    public static Email createMock() {
        return Email.builder()
                .code(UUID.randomUUID().toString())
                .subject("kafka.cancian@gmail.com")
                .body("This is the body of a email test using kafka")
                .build();
    }
}
