package com.kjone.useroauth.domain.friend.service;

import com.kjone.useroauth.domain.friend.dto.response.FriendResponse;

import java.util.List;

public interface FriendService {
    public void addFriend(Long friendId);
    public List<FriendResponse> getFriends();
    List<FriendResponse> getBeFriends();
    public void removeFriend(Long friendId);

}
