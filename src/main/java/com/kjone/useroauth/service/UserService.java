package com.kjone.useroauth.service;


import com.kjone.useroauth.entity.UserEntity;
import com.kjone.useroauth.request.LoginRequest;
import com.kjone.useroauth.request.SignRequest;
import com.kjone.useroauth.response.LoginResponse;
import com.kjone.useroauth.response.SignResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    public boolean signUp(SignRequest signRequest) throws Exception;
    public LoginResponse signIn(LoginRequest loginRequest, HttpServletResponse response) throws Exception;
    LoginResponse refreshToken(String refreshToken, HttpServletResponse response) throws Exception;

}
