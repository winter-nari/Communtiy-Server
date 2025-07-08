package com.kjone.useroauth.entity;


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
    private BoardEntity board;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
