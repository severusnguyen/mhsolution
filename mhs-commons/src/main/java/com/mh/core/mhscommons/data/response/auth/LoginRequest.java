package com.mh.core.mhscommons.data.response.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
