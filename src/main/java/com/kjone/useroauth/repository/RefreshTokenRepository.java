package com.kjone.useroauth.repository;

import com.kjone.useroauth.entity.RefreshTokenEntity;
import com.kjone.useroauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByUser(UserEntity user);

    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByUserId(Long userId);
}
