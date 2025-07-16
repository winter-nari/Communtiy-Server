package com.kjone.useroauth.domain.image.entity;


import com.kjone.useroauth.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;    // 예: "abc123.png"
    private String filePath;    // 예: "/uploads/abc123.png"
    private String fileType;    // 예: "image/png"
    private Long fileSize;      // 바이트 단위

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
