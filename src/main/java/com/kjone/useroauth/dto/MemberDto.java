package com.kjone.useroauth.dto;

import com.kjone.useroauth.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String username;
    private String phone;
    private Integer age;
    private String sex;

    public static MemberDto fromEntity(UserEntity user) {
        return MemberDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .age(user.getAge())
                .sex(user.getSex())
                .build();
    }
}
