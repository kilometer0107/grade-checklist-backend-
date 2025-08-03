package com.team.grade_checklist.domain.user.controller;

import com.team.grade_checklist.domain.user.dto.request.LoginRequest;
import com.team.grade_checklist.domain.user.dto.request.SignupRequest;
import com.team.grade_checklist.domain.user.dto.response.LoginResponse;
import com.team.grade_checklist.domain.user.dto.response.SignupResponse;
import com.team.grade_checklist.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> signUp(@ResponseBody SignupRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }
    //TODO: 로그인 API 구현
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
