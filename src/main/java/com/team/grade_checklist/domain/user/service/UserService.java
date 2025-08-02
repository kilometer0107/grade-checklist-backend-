package com.team.grade_checklist.domain.user.service;

import com.team.grade_checklist.domain.user.dto.UserInfoResponse;
import com.team.grade_checklist.domain.user.dto.UserUpdateRequest;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(String studentId) { //사용자 정보 조회
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("해당 학번의 사용자가 존재하지 않습니다."));
        return UserInfoResponse.from(user);
    }

    public void updateUserInfo(String studentId, UserUpdateRequest request) { //사용자 정보 수정
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("해당 학번의 사용자가 존재하지 않습니다."));

        user.updateInfo(request.getName(), request.getDepartment(), request.getAdmissionYear(), request.getGraduationTrack());
    }

    //비밀번호 변경 메서드 별도 구현 필요
}
