package com.kjone.useroauth.domain.oauth.service;


import com.kjone.useroauth.domain.oauth.dto.request.LoginRequest;
import com.kjone.useroauth.domain.oauth.dto.request.SignRequest;
import com.kjone.useroauth.domain.oauth.dto.response.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    public boolean signUp(SignRequest signRequest) throws Exception;
    public LoginResponse signIn(LoginRequest loginRequest, HttpServletResponse response) throws Exception;
    LoginResponse refreshToken(String refreshToken, HttpServletResponse response) throws Exception;

}
