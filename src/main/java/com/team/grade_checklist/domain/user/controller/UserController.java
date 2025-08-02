package com.team.grade_checklist.domain.user.controller;

import com.team.grade_checklist.domain.user.service.UserService;
import com.team.grade_checklist.domain.user.dto.UserInfoResponse;
import com.team.grade_checklist.domain.user.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestHeader("student_id") String studentId) {
        //@RequestHeader("student-id")는 JWT 기반으로 교체해야 함
        UserInfoResponse response = userService.getUserInfo(studentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Void> updateUserInfo(@RequestHeader("student_id") String studentId, @RequestBody UserUpdateRequest request) {
        //@RequestHeader("student-id")는 JWT 기반으로 교체해야 함
        userService.updateUserInfo(studentId, request);
        return ResponseEntity.ok().build();
    }
}
