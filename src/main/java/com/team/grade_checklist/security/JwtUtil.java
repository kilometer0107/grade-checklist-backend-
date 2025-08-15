package com.team.grade_checklist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

//jwt
@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY;
    private final long EXPIRATION_TIME;

    public JwtUtil(
            @Value("${jwt.secret:your-super-secret-key-for-jwt-signing-must-be-at-least-256-bits-long}") String secretKey,
            @Value("${jwt.expiration:86400000}") long expirationTime
    ) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.EXPIRATION_TIME = expirationTime;
    }

    //토큰 생성 -> hs256이용
    public String generateToken(String studentId) {
        return Jwts.builder()
                .setSubject(studentId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    //토큰 파싱으로 학번 추출
    public String getStudentIdFromToken(String token) {
        try {
            // 토큰 파싱 및 검증
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    public boolean validateToken(String token) {
        try {
            // 토큰 파싱 및 검증
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 만료시간 확인
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}