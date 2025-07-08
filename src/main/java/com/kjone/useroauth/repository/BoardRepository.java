package com.kjone.useroauth.repository;

import com.kjone.useroauth.entity.BoardEntity;
import com.kjone.useroauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.image WHERE b.id = :id")
    Optional<BoardEntity> findBoardWithImage(@Param("id") Long id);

    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.image")
    List<BoardEntity> findAllWithImage();

    Optional<BoardEntity> findByName(String name);
}
