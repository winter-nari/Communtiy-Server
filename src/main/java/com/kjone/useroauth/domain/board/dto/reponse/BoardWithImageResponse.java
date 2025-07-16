package com.kjone.useroauth.domain.board.dto.reponse;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardWithImageResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
