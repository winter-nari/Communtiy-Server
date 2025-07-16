package com.kjone.useroauth.domain.friend.repository;

import com.kjone.useroauth.domain.friend.entity.Friend;
import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser(UserEntity user);

    // 나를 친구로 추가한 사람 목록 (friend 기준)
    List<Friend> findByFriend(UserEntity friend);

    boolean existsByUserAndFriend(UserEntity user, UserEntity friend);

    Optional<Friend> findByUserAndFriend(UserEntity user, UserEntity friend);

    List<Friend> findByNicknameContaining(String keyword);

}
