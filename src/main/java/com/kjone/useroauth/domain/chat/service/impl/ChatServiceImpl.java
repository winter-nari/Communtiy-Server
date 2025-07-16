package com.kjone.useroauth.domain.chat.service.impl;

import com.kjone.useroauth.domain.chat.entity.ChatRoom;
import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import com.kjone.useroauth.domain.chat.entity.UserRoom;
import com.kjone.useroauth.domain.chat.repository.ChatRoomRepository;
import com.kjone.useroauth.domain.oauth.repository.UserRepository;
import com.kjone.useroauth.global.security.service.UserForSecurity;
import com.kjone.useroauth.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 1:1 DM 방 찾기 또는 새로 만들기
    @Transactional
    @Override
    public Long createOrGetRoom(Long friendId) {
        Long myId = getCurrentUserId();

        ChatRoom existingRoom = chatRoomRepository.findRoomByParticipants(myId, friendId);
        if (existingRoom != null) {
            return existingRoom.getId();
        }

        UserEntity me = userRepository.findById(myId)
                .orElseThrow(() -> new RuntimeException("내 계정이 존재하지 않습니다."));
        UserEntity friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("친구 계정이 존재하지 않습니다."));

        ChatRoom room = ChatRoom.builder()
                .name(null)
                .build();

        UserRoom myRoom = UserRoom.builder()
                .user(me)
                .build();

        UserRoom friendRoom = UserRoom.builder()
                .user(friend)
                .build();

        room.addParticipant(myRoom);
        room.addParticipant(friendRoom);

        chatRoomRepository.save(room);

        return room.getId();
    }

    // 현재 로그인한 사용자 정보 가져오는 메서드 (안전하게 캐스팅)
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserForSecurity)) {
            throw new RuntimeException("로그인 정보가 없습니다.");
        }

        UserForSecurity userDetails = (UserForSecurity) authentication.getPrincipal();
        return userDetails.getId();
    }



    // 그룹 채팅방에 유저 초대
    @Transactional
    @Override
    public void inviteUser(Long roomId, Long newUserId) {

        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("채팅방이 존재하지 않음"));

        UserEntity newUser = userRepository.findById(newUserId)
                .orElseThrow(() -> new RuntimeException("초대할 유저가 존재하지 않음"));

        UserRoom newUserRoom = UserRoom.builder()
                .user(newUser)
                .chatRoom(room)
                .build();

        room.getParticipants().add(newUserRoom);

        chatRoomRepository.save(room);
    }
}

