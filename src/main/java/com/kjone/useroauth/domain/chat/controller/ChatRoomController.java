package com.kjone.useroauth.domain.chat.controller;

import com.kjone.useroauth.domain.chat.dto.response.ChatRoomSummaryResponse;
import com.kjone.useroauth.domain.chat.entity.ChatMessages;
import com.kjone.useroauth.domain.chat.entity.ChatRoom;
import com.kjone.useroauth.domain.chat.entity.UserRoom;
import com.kjone.useroauth.domain.chat.dto.response.ChatMessageResponse;
import com.kjone.useroauth.domain.chat.repository.ChatMessagesRepository;
import com.kjone.useroauth.domain.chat.repository.ChatRoomRepository;
import com.kjone.useroauth.global.security.service.UserForSecurity;
import com.kjone.useroauth.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessagesRepository chatMessagesRepository;

    @PostMapping
    public Long createOrGetRoom(@RequestParam Long friendId) {
        return chatRoomService.createOrGetRoom(friendId);
    }

    @GetMapping("/{roomId}/messages")
    public List<ChatMessageResponse> getMessages(@PathVariable Long roomId) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("채팅방이 존재하지 않습니다."));

        List<ChatMessages> messages = chatMessagesRepository.findByChatRoomOrderBySentAtAsc(room);

        return messages.stream().map(msg -> {
            ChatMessageResponse dto = new ChatMessageResponse();
            dto.setSender(msg.getSender());
            dto.setContent(msg.getContent());
            dto.setType(ChatMessageResponse.MessageType.CHAT);
            return dto;
        }).toList();
    }

    @GetMapping("/my")
    public List<ChatRoomSummaryResponse> getMyRooms(@AuthenticationPrincipal UserForSecurity user) {
        Long myId = user.getId();
        List<ChatRoom> myRooms = chatRoomRepository.findAllByParticipantId(myId);

        return myRooms.stream().map(room -> {
            // 나를 제외한 상대방 가져오기
            UserRoom other = room.getParticipants().stream()
                    .filter(ur -> !ur.getUser().getId().equals(myId))
                    .findFirst()
                    .orElseThrow(); // 그룹 채팅이면 이 부분 수정 필요

            return new ChatRoomSummaryResponse(
                    room.getId(),
                    other.getUser().getUsername(),
                    other.getUser().getEmail()
            );
        }).toList();
    }

    @PostMapping("/{roomId}/invite")
    public void inviteUser(@PathVariable Long roomId, @RequestParam Long newUserId) {
        chatRoomService.inviteUser(roomId, newUserId);
    }
}
