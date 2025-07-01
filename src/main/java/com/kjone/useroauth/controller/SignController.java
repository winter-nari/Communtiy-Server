package com.kjone.useroauth.controller;

import com.kjone.useroauth.request.LoginRequest;
import com.kjone.useroauth.request.SignRequest;
import com.kjone.useroauth.response.LoginResponse;
import com.kjone.useroauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) throws Exception {
        LoginResponse loginResponse = userService.signIn(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignRequest signRequest) throws Exception {
        userService.signUp(signRequest);
        return ResponseEntity
                .ok()
                .build();
    }
}