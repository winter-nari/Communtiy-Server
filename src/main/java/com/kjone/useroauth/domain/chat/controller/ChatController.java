package com.kjone.useroauth.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage() {
        return "index"; // templates/chat.html 을 반환 (Thymeleaf 사용 시)
    }
}
