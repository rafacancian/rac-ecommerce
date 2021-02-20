package com.ecommerce.serviceuser.services;

import com.ecommerce.model.User;
import com.ecommerce.serviceuser.adapters.UserAdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public User findUserByEmail(String email) {
        return UserAdater.createMock();
    }

    public List<User> findAllUsers() {
        return UserAdater.findAllMock();
    }
}
