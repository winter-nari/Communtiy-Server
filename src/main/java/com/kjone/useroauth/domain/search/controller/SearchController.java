package com.kjone.useroauth.domain.search.controller;


import com.kjone.useroauth.domain.board.repository.BoardRepository;
import com.kjone.useroauth.domain.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final BoardRepository boardRepository;
    private final FriendRepository friendRepository;

    @GetMapping
    public Map<String, Object> search(@RequestParam String keyword,
                                      @RequestParam(required = false) String type) {

        Map<String, Object> result = new HashMap<>();

        if ("board".equals(type)) {
            result.put("boards", boardRepository.findByTitleContaining(keyword));
        } else if ("friend".equals(type)) {
            result.put("friends", friendRepository.findByNicknameContaining(keyword));
        } else {
            result.put("boards", boardRepository.findByTitleContaining(keyword));
            result.put("friends", friendRepository.findByNicknameContaining(keyword));
        }

        return result;
    }
}

