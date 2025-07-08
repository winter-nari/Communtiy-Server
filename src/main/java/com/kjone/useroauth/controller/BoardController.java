package com.kjone.useroauth.controller;


import com.kjone.useroauth.dto.BoardCreateRequestDto;
import com.kjone.useroauth.dto.BoardWithImageDto;
import com.kjone.useroauth.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/id/{boardId}")
    public BoardWithImageDto getBoard(@PathVariable Long boardId) {
        return boardService.getBoardWithImage(boardId);
    }

    @GetMapping
    public List<BoardWithImageDto> getAllBoards() {
        return boardService.getAllBoardsWithImage();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoard(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        // 직접 BoardCreateRequestDto 대신 개별 필드로 객체 생성
        BoardCreateRequestDto dto = new BoardCreateRequestDto();
        dto.setName(name);
        dto.setDescription(description);

        BoardWithImageDto result = boardService.createBoardWithImage(dto, imageFile);
        return ResponseEntity.ok(result);
    }

}
