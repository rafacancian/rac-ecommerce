package com.ecommerce.serviceuser.adapters;


import com.ecommerce.serviceuser.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserAdater {

    public static User createMock() {
        return newUserMock("rafacancian00", "Rafael Cancian 00", "Rua 0 de maio - Parque das nacoes - Lisboa", "rafael00@example.com", "000.000.000.00");
    }

    public static List<User> findAllMock() {
        List<User> users = new ArrayList<>();
        users.add(newUserMock("rafacancian01", "Rafael Cancian 01", "Rua 1 de maio - Parque das nacoes - Lisboa", "rafael01@example.com", "111.111.111.11"));
        users.add(newUserMock("rafacancian02", "Rafael Cancian 02", "Rua 2 de maio - Parque das nacoes - Lisboa", "rafael02@example.com", "222.222.222.22"));
        users.add(newUserMock("rafacancian03", "Rafael Cancian 03", "Rua 3 de maio - Parque das nacoes - Lisboa", "rafael03@example.com", "333.333.333.33"));
        users.add(newUserMock("rafacancian04", "Rafael Cancian 04", "Rua 4 de maio - Parque das nacoes - Lisboa", "rafael04@example.com", "444.444.444.44"));
        users.add(newUserMock("rafacancian05", "Rafael Cancian 05", "Rua 5 de maio - Parque das nacoes - Lisboa", "rafael05@example.com", "555.555.555.55"));
        users.add(newUserMock("rafacancian06", "Rafael Cancian 06", "Rua 6 de maio - Parque das nacoes - Lisboa", "rafael06@example.com", "666.666.666.66"));
        users.add(newUserMock("rafacancian07", "Rafael Cancian 07", "Rua 7 de maio - Parque das nacoes - Lisboa", "rafael07@example.com", "777.777.777.77"));
        return users;
    }

    private static User newUserMock(String username, String fullName, String address, String email, String cpf) {
        return User.builder()
                .code(UUID.randomUUID().toString())
                .username(username)
                .fullName(fullName)
                .address(address)
                .email(email)
                .cpf(cpf)
                .build();
    }
}
