package com.team.grade_checklist.domain.user.controller;

import com.team.grade_checklist.domain.user.dto.request.LoginRequest;
import com.team.grade_checklist.domain.user.dto.request.RegisterRequest;
import com.team.grade_checklist.domain.user.dto.response.AuthResponse;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.service.AuthService;
import com.team.grade_checklist.security.JwtUtil;
import com.team.grade_checklist.security.TokenBlacklistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 인증 관련 controller
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(AuthService authService, JwtUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    //회원가입
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);
            String token = jwtUtil.generateToken(user.getStudentId());
            return ResponseEntity.ok(AuthResponse.success(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(AuthResponse.failure(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(AuthResponse.failure("회원가입 중 오류가 발생했습니다."));
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            User user = authService.login(request.getStudentId(), request.getPassword());

            if (user != null) {
                String token = jwtUtil.generateToken(user.getStudentId());
                return ResponseEntity.ok(AuthResponse.success(token));
            } else {
                return ResponseEntity.badRequest().body(AuthResponse.failure("학번 또는 비밀번호가 올바르지 않습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(AuthResponse.failure("로그인 중 오류가 발생했습니다."));
        }
    }

    //토큰검증
    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                String actualToken = token.substring(7);
                if (jwtUtil.validateToken(actualToken)) {
                    return ResponseEntity.ok(AuthResponse.success(actualToken));
                }
            }
            return ResponseEntity.badRequest().body(AuthResponse.failure("유효하지 않은 토큰입니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(AuthResponse.failure("토큰 검증 중 오류가 발생했습니다."));
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                String actualToken = token.substring(7);
                tokenBlacklistService.blacklistToken(actualToken);
                return ResponseEntity.ok(AuthResponse.success(null));
            }
            return ResponseEntity.badRequest().body(AuthResponse.failure("토큰이 필요합니다."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(AuthResponse.failure("로그아웃 중 오류가 발생했습니다."));
        }
    }
}
