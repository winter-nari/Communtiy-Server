package com.kjone.useroauth.response;


import com.kjone.useroauth.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {
    private Long id; // key 아이디 & 식별번호
    private String email; // 이메일
    private String password; // 비번
    private String username; // 이름
    private String phone; // 전화번호
    private int age; // 나이
    private String sex; //성별
    private String image;



    public SignResponse(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.age = user.getAge();
        this.sex = user.getSex().toString();
        this.image = user.getImage();
    }

}