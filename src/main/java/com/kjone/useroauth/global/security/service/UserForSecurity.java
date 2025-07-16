package com.kjone.useroauth.global.security.service;

import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
public class UserForSecurity implements UserDetails {

    private final PasswordEncoder passwordEncoder;

    @NonNull
    private UserEntity user;

    // 현재 로그인한 유저의 ID 가져오기
    public Long getId() {
        return user.getId();
    }

    //일단 테스트
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
