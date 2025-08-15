package com.team.grade_checklist.security;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//로그아웃된 토큰 관리 (24시간 후 자동 삭제)
@Service
public class TokenBlacklistService {

    private final Map<String, LocalDateTime> blacklistedTokens = new ConcurrentHashMap<>();

    //토큰-> 블랙리스트에 추가
    public void blacklistToken(String token) {
        blacklistedTokens.put(token, LocalDateTime.now().plusHours(24));
    }

    //토큰이 블랙리스트에 있는지 확인
    public boolean isTokenBlacklisted(String token) {
        LocalDateTime expiryTime = blacklistedTokens.get(token);

        if (expiryTime == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(expiryTime)) {
            blacklistedTokens.remove(token); // 만료된 토큰 삭제
            return false;
        }

        return true;
    }
}