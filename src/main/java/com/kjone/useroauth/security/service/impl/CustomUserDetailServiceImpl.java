package com.kjone.useroauth.security.service.impl;

import com.kjone.useroauth.entity.UserEntity;
import com.kjone.useroauth.repository.UserRepository;
import com.kjone.useroauth.security.service.CustomUserDetailService;
import com.kjone.useroauth.security.service.UserForSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return UserForSecurity.builder()
                .user(user)
                .build();
    }

    @Override
    public UserDetails loadUserById(String userId) throws UsernameNotFoundException {
        // userId 타입이 Long이면 아래처럼, UUID면 UUID.fromString(userId)로 변경
        UserEntity user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
        return UserForSecurity.builder()
                .user(user)
                .build();
    }
}
