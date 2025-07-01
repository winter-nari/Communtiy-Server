package com.kjone.useroauth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String code;
    private int status;
    private LoginData data;
}
