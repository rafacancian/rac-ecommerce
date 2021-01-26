package com.ecommerce.serviceuser.services;

import com.ecommerce.serviceuser.adapters.UserAdater;
import com.ecommerce.serviceuser.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUserByEmail(String email) {
        return UserAdater.createMock();
    }
}
