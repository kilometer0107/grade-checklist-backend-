package com.team.grade_checklist.domain.user.service;

import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjects;
import com.team.grade_checklist.domain.boxes.repository.EnrolledSubjectsRepository;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.repository.SubjectsRepository;
import com.team.grade_checklist.domain.user.dto.request.RegisterRequest;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 회원가입, 로그인 등 인증/인가 관련
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubjectsRepository subjectsRepository;
    private final EnrolledSubjectsRepository enrolledSubjectsRepository;

    // 회원가입 처리
    @Transactional
    public User register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.existsByStudentId(request.getStudentId())) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }

        User user = User.builder()
                .studentId(request.getStudentId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .department((request.getDepartment() != null) ? request.getDepartment() : Majors.ETC)
                .admissionYear(request.getAdmissionYear())
                .build();


        return userRepository.save(user);

    }

    // 로그인 처리
    @Transactional(readOnly = true)
    public User login(String studentId, String password) {
        User user = userRepository.findByStudentId(studentId).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
