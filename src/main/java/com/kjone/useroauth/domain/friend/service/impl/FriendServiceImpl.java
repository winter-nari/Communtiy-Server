package com.kjone.useroauth.domain.friend.service.impl;

import com.kjone.useroauth.domain.friend.dto.response.FriendResponse;
import com.kjone.useroauth.domain.friend.entity.Friend;
import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import com.kjone.useroauth.domain.friend.repository.FriendRepository;
import com.kjone.useroauth.domain.oauth.repository.UserRepository;
import com.kjone.useroauth.global.security.service.UserForSecurity;
import com.kjone.useroauth.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 현재 로그인한 사용자 정보 가져오는 메서드
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 사용자 정보 꺼내기
        UserForSecurity userDetails = (UserForSecurity) authentication.getPrincipal();

        return userDetails.getId(); // 여기서 유저 ID 가져오기
    }

    @Transactional
    @Override
    public void addFriend(Long friendId) {
        Long userId = getCurrentUserId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        UserEntity friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 없음"));

        if (friendRepository.existsByUserAndFriend(user, friend)) {
            throw new IllegalStateException("이미 친구입니다.");
        }

        friendRepository.save(Friend.builder()
                .user(user)
                .friend(friend)
                .build());
    }

    @Override
    public List<FriendResponse> getFriends() {
        Long userId = getCurrentUserId();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        List<Friend> friendEntities = friendRepository.findByUser(user);

        return friendEntities.stream()
                .map(f -> FriendResponse.builder()
                        .friendId(f.getFriend().getId())
                        .friendEmail(f.getFriend().getEmail())
                        .friendUsername(f.getFriend().getUsername())
                        .friendImage(f.getFriend().getImage())
                        .build())
                .toList();
    }

    // 나를 친구 추가한 사람 목록
    @Override
    public List<FriendResponse> getBeFriends() {
        Long userId = getCurrentUserId();
        UserEntity me = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        List<Friend> beFriends = friendRepository.findByFriend(me);

        return beFriends.stream()
                .map(f -> FriendResponse.from(f.getUser()))
                .toList();
    }



    @Transactional
    @Override
    public void removeFriend(Long friendId) {
        Long userId = getCurrentUserId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        UserEntity friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 없음"));

        Friend relation = friendRepository.findByUserAndFriend(user, friend)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않음"));

        friendRepository.delete(relation);
    }
}
