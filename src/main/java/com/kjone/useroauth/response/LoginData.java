package com.kjone.useroauth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {
    private String accessToken;
    private String refreshToken;
}
