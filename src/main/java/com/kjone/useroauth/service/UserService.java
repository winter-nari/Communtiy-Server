package com.kjone.useroauth.service;


import com.kjone.useroauth.entity.UserEntity;
import com.kjone.useroauth.request.LoginRequest;
import com.kjone.useroauth.request.SignRequest;
import com.kjone.useroauth.response.LoginResponse;
import com.kjone.useroauth.response.SignResponse;

public interface UserService {
    public boolean signUp(SignRequest signRequest) throws Exception;
    public LoginResponse signIn(LoginRequest loginRequest) throws Exception;


}
