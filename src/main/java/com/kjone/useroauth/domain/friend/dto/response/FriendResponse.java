package com.kjone.useroauth.domain.friend.dto.response;

import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendResponse {
    private Long friendId;
    private String friendEmail;
    private String friendUsername;
    private String friendImage;
    public static FriendResponse from(UserEntity user) {
        return FriendResponse.builder()
                .friendId(user.getId())
                .friendUsername(user.getUsername())
                .friendEmail(user.getEmail())
                .friendImage(user.getImage()) // 필요 시
                .build();
    }
}
