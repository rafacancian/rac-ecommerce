package com.ecommerce.applicationmanager.adapters;


import com.ecommerce.applicationmanager.models.User;

import java.util.UUID;

public class UserAdater {

    public static User createMock() {
        return User.builder()
                .code(UUID.randomUUID().toString())
                .username("rafacancian")
                .fullName("Rafael Cancian")
                .address("Rua 1 de maio - Parque das nacoes - Lisboa")
                .email("kafka.cancian@gmail.com")
                .identifier("111.222.333-45")
                .build();
    }
}
