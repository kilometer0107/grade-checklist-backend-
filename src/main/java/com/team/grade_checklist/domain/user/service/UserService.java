package com.team.grade_checklist.domain.user.service;


import com.team.grade_checklist.domain.user.dto.request.LoginRequest;
import com.team.grade_checklist.domain.user.dto.request.SignupRequest;
import com.team.grade_checklist.domain.user.dto.response.LoginResponse;
import com.team.grade_checklist.domain.user.dto.response.SignupResponse;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.enums.Major;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import com.team.grade_checklist.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional
    public SignupResponse signUp(SignupRequest request) {
        String name = request.name();
        String password = request.password();
        Integer studentId = request.studentId();
        String email = request.email();
        Major major = request.major();

        User user = User.builder()
                .name(name)
                .password(password)
                .studentId(studentId)
                .email(email)
                .major(major)
                .build();

        userRepository.save(user);

        String accessToken = TokenProvider.generateToken(user, Duration.ofDays(1));

        return new SignupResponse(accessToken);
    }
    //TODO: 로그인 구현
    @Transactional
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByStudentId(request.studentId())
                .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 이메일입니다"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String accessToken = TokenProvider.generateToken(user, Duration.ofDays(1));

        return new LoginResponse(accessToken, user.getName());
    }
}
