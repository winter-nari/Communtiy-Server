package com.kjone.useroauth.domain.oauth.repository;

import com.kjone.useroauth.domain.oauth.entity.RefreshToken;
import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUser(UserEntity user);

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);
}
