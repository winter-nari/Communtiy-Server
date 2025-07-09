package com.kjone.useroauth.controller;

import com.kjone.useroauth.model.ChatMessage;
import com.kjone.useroauth.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserSessionService userSessionService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        String username = chatMessage.getSender();

        // 닉네임 중복 확인
        if (!userSessionService.addUser(username)) {
            ChatMessage errorMessage = new ChatMessage();
            errorMessage.setType(ChatMessage.MessageType.ERROR);
            errorMessage.setContent("같은 이름이 이미 등록되어 있습니다.");
            return errorMessage;
        }

        // 세션에 유저 이름 저장
        headerAccessor.getSessionAttributes().put("username", username);
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        return chatMessage;
    }
}
