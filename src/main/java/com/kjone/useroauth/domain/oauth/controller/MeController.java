package com.kjone.useroauth.domain.oauth.controller;


import com.kjone.useroauth.domain.oauth.dto.response.MemberResponse;
import com.kjone.useroauth.domain.oauth.service.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {

    private final MeService meService;

    @GetMapping
    public ResponseEntity<MemberResponse> getMyInfo() {
        MemberResponse memberResponse = meService.me();
        return ResponseEntity.ok(memberResponse);
    }
}
