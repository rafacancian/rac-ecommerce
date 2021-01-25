package com.ecommerce.frauddetector.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private String code;
    private String username;
    private String fullName;
    private String address;
    private String email;
    private String identifier;
}
