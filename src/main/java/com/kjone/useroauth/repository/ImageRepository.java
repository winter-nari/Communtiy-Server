package com.kjone.useroauth.repository;

import com.kjone.useroauth.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // 필요한 커스텀 쿼리도 여기에 추가 가능
}
