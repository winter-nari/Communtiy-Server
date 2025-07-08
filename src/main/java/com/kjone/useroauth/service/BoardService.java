package com.kjone.useroauth.service;

import com.kjone.useroauth.dto.BoardCreateRequestDto;
import com.kjone.useroauth.dto.BoardWithImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    public BoardWithImageDto getBoardWithImage(Long boardId);
    public List<BoardWithImageDto> getAllBoardsWithImage();
    public BoardWithImageDto createBoardWithImage(BoardCreateRequestDto dto, MultipartFile imageFile);
}
