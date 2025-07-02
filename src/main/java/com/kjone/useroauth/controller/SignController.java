package com.kjone.useroauth.controller;

import com.kjone.useroauth.request.LoginRequest;
import com.kjone.useroauth.request.SignRequest;
import com.kjone.useroauth.response.LoginResponse;
import com.kjone.useroauth.security.cookie.CookieUtil;
import com.kjone.useroauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        LoginResponse loginResponse = userService.signIn(loginRequest, response);
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

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String refreshToken = CookieUtil.getCookieValue(request, "refreshToken");
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LoginResponse loginResponse = userService.refreshToken(refreshToken, response);
        return ResponseEntity.ok(loginResponse);
    }
}