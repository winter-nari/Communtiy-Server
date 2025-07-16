package com.kjone.useroauth.domain.board.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoardCreateRequest {
    private String name;
    private String description;
    private MultipartFile image;
}
