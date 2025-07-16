package com.kjone.useroauth.domain.friend.controller;


import com.kjone.useroauth.domain.friend.dto.response.FriendResponse;
import com.kjone.useroauth.domain.friend.repository.FriendRepository;
import com.kjone.useroauth.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
    private final FriendRepository friendRepository;


    // 친구 추가
    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam Long friendId) {
        friendService.addFriend(friendId);
        return ResponseEntity.ok("친구 추가 완료");
    }

    // 친구 삭제
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFriend(@RequestParam Long friendId) {
        friendService.removeFriend(friendId);
        return ResponseEntity.ok("친구 삭제 완료");
    }

    // 친구 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<FriendResponse>> getFriendList() {
        List<FriendResponse> friends = friendService.getFriends();
        return ResponseEntity.ok(friends);
    }

    // 나를 친구 추가한 사람 목록 조회
    @GetMapping("/befriends")
    public ResponseEntity<List<FriendResponse>> getBeFriends() {
        List<FriendResponse> beFriends = friendService.getBeFriends();
        return ResponseEntity.ok(beFriends);
    }
}
