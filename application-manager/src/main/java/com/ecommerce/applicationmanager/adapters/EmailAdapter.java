package com.ecommerce.applicationmanager.adapters;


import com.ecommerce.model.Email;

import java.util.UUID;

public class EmailAdapter {

    public static Email createMock(String subject) {
        return Email.builder()
                .code(UUID.randomUUID().toString().substring(0, 15))
                .subject(subject)
                .body("This is the body of a email test using kafka")
                .build();
    }
}
