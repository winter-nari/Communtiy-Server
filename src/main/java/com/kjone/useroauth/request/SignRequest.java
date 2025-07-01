package com.kjone.useroauth.request;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class SignRequest {
    private UUID Id;
    private String email;
    private String password;
    private String username;
    private String phone;
    private int age;
    private String sex;
    private String image;
}