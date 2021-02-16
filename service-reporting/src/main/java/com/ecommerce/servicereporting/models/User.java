package com.ecommerce.servicereporting.models;

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
    private String cpf;

    public String getReportPath() {
        return "target/" + code + "report.txt";
    }
}
