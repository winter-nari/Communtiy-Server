package com.kjone.useroauth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardWithImageDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
