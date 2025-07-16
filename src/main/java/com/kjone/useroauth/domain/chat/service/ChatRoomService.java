package com.kjone.useroauth.domain.chat.service;

public interface ChatRoomService {
    public Long createOrGetRoom(Long friendId);
    public void inviteUser(Long roomId, Long newUserId);
}
