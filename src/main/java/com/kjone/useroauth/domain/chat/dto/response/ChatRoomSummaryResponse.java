package com.kjone.useroauth.domain.chat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomSummaryResponse {
    private Long roomId;
    private String otherUsername;
    private String otherEmail;
//    private String otherImage;
}
