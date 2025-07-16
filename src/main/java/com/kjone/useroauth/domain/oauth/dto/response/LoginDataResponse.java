package com.kjone.useroauth.domain.oauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDataResponse {
    private String accessToken;
    private String refreshToken;
}
