package com.kjone.useroauth.domain.board.service;

import com.kjone.useroauth.domain.board.dto.request.BoardCreateRequest;
import com.kjone.useroauth.domain.board.dto.reponse.BoardWithImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    public BoardWithImageResponse getBoardWithImage(Long boardId);
    public List<BoardWithImageResponse> getAllBoardsWithImage();
    public BoardWithImageResponse createBoardWithImage(BoardCreateRequest dto, MultipartFile imageFile);
}
