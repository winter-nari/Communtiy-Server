package com.kjone.useroauth.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardCreateRequestDto {
    private String name;
    private String description;
    private MultipartFile image;
}
