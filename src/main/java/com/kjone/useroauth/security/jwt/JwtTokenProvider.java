package com.kjone.useroauth.security.jwt;

import com.kjone.useroauth.entity.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key key;

    private final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60;       // 1시간
    private final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24; // 1일

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 토큰 생성 시 userId (Long) 를 subject로 넣음
    public String createAccessToken(UserEntity user) {
        return createToken(user, ACCESS_TOKEN_VALID_TIME, "ACCESS_TOKEN");
    }

    public String createRefreshToken(UserEntity user) {
        return createToken(user, REFRESH_TOKEN_VALID_TIME, "REFRESH_TOKEN");
    }

    private String createToken(UserEntity user, long validity, String type) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId())) // Long id를 문자열로
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validity))
                .claim("type", type)
                .claim("email", user.getEmail())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("[JWT 오류] " + e.getMessage());
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }
}


