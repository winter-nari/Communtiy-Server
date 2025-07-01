package com.kjone.useroauth.controller;


import com.kjone.useroauth.dto.MemberDto;
import com.kjone.useroauth.service.MeService;
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
    public ResponseEntity<MemberDto> getMyInfo() {
        MemberDto memberDto = meService.me();
        return ResponseEntity.ok(memberDto);
    }
}
