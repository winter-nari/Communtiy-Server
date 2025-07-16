package com.kjone.useroauth.domain.chat.repository;

import com.kjone.useroauth.domain.chat.entity.ChatMessages;
import com.kjone.useroauth.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {
    List<ChatMessages> findByChatRoomOrderBySentAtAsc(ChatRoom chatRoom);

}
