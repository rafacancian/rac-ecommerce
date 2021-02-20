package com.ecommerce.applicationmanager.adapters;

import com.ecommerce.model.User;

import java.util.UUID;

public class UserAdapter {

    public static User createMock() {
        return User.builder()
                .code(UUID.randomUUID().toString())
                .username("rafacancian")
                .fullName("Rafael Cancian")
                .address("Rua 1 de maio - Parque das nacoes - Lisboa")
                .email("kafka.cancian@gmail.com")
                .cpf("111.222.333-45")
                .build();
    }
}
