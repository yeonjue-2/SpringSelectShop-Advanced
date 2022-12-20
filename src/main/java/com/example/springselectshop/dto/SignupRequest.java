package com.example.springselectshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}