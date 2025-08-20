package com.team.grade_checklist.domain.user.service;

import com.team.grade_checklist.domain.user.dto.request.UserUpdateRequest;
import com.team.grade_checklist.domain.user.dto.response.UserResponse;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

// 사용자 정보 조회/수정 등 사용자 자체 관리
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //사용자 정보 조회
    @Transactional(readOnly = true)
    public UserResponse getUserInfo(String studentId) {
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        return new UserResponse(
                user.getStudentId(),
                user.getName(),
                user.getDepartment(),
                user.getAdmissionYear(),
                user.getAverageGrade()
        );
    }

    //사용자 정보 수정
    @Transactional
    public void updateUserInfo(String studentId, UserUpdateRequest request) {
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));

        user.updateInfo(
                request.getName(),
                request.getDepartment(),
                request.getAdmissionYear(),
                request.getAverageGrade()
        );
    }

    // 비밀번호 변경 메서드 별도 구현 필요
}
