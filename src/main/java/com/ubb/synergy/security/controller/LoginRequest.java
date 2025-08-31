package com.ubb.synergy.security.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
