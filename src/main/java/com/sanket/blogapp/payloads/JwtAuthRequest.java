package com.sanket.blogapp.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
