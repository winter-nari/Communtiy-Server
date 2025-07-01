package com.kjone.useroauth.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailService extends UserDetailsService {
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    // 추가: userId로 조회
    UserDetails loadUserById(String userId) throws UsernameNotFoundException;
}
