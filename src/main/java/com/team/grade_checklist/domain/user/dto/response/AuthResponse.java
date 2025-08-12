package com.team.grade_checklist.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//인증응답
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String message;
    private boolean success;

    public static AuthResponse success(String token) {
        return new AuthResponse(token, "인증 성공", true);
    }

    public static AuthResponse failure(String message) {
        return new AuthResponse(null, message, false);
    }
}