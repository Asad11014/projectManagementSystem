package com.asad.project_management_system.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
