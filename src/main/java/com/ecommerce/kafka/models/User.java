package com.ecommerce.kafka.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String code;
    private String username;
    private String fullName;
    private String address;
    private String email;
    private String identifier;
}
