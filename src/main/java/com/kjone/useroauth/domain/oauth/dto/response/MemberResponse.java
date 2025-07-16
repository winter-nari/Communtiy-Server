package com.kjone.useroauth.domain.oauth.dto.response;

import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {
    private Long id;
    private String email;
    private String username;
    private String phone;
    private Integer age;
    private String sex;

    public static MemberResponse fromEntity(UserEntity user) {
        return MemberResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .age(user.getAge())
                .sex(user.getSex())
                .build();
    }
}
