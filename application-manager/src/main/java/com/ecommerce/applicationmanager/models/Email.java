package com.ecommerce.applicationmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Email implements Serializable {

    private String code;
    private String subject;
    private String body;

}
