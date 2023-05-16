package com.springsecuritypractice.config.jwt.dto;

import lombok.Data;

@Data
public class AuthRequest {
    String username;
    String password;
}
