package com.kjone.useroauth.domain.friend.entity;


import com.kjone.useroauth.domain.oauth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friend")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private UserEntity friend;

}
