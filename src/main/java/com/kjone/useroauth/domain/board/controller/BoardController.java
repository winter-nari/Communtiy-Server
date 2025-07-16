package com.kjone.useroauth.domain.board.controller;


import com.kjone.useroauth.domain.board.dto.request.BoardCreateRequest;
import com.kjone.useroauth.domain.board.dto.reponse.BoardWithImageResponse;
import com.kjone.useroauth.domain.board.service.BoardService;
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
    public BoardWithImageResponse getBoard(@PathVariable Long boardId) {
        return boardService.getBoardWithImage(boardId);
    }

    @GetMapping
    public List<BoardWithImageResponse> getAllBoards() {
        return boardService.getAllBoardsWithImage();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoard(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        // 직접 BoardCreateRequestDto 대신 개별 필드로 객체 생성
        BoardCreateRequest dto = new BoardCreateRequest();
        dto.setName(name);
        dto.setDescription(description);

        BoardWithImageResponse result = boardService.createBoardWithImage(dto, imageFile);
        return ResponseEntity.ok(result);
    }

}
