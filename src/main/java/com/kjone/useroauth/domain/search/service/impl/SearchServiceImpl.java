package com.kjone.useroauth.domain.search.service.impl;


import com.kjone.useroauth.domain.board.service.BoardService;
import com.kjone.useroauth.domain.friend.service.FriendService;
import com.kjone.useroauth.domain.oauth.service.UserService;
import com.kjone.useroauth.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final UserService userService;
    private final BoardService boardService;
    private final FriendService friendService;




}
