package com.kjone.useroauth.domain.oauth.service.impl;

import com.kjone.useroauth.domain.oauth.dto.response.MemberResponse;
import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import com.kjone.useroauth.domain.oauth.repository.UserRepository;
import com.kjone.useroauth.global.security.service.UserForSecurity;
import com.kjone.useroauth.domain.oauth.service.MeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MeServiceImpl implements MeService {

    private final UserRepository userRepository;

    @Override
    public MemberResponse me() {
        // 현재 인증된 UserDetails 꺼내기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserForSecurity)) {
            throw new RuntimeException("인증된 사용자가 없습니다.");
        }

        UserForSecurity userDetails = (UserForSecurity) principal;
        Long userId = userDetails.getUser().getId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        return MemberResponse.fromEntity(user);
    }
}
