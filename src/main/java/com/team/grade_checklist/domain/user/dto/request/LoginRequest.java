package com.team.grade_checklist.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//로그인요청
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "학번은 필수입니다")
    private String studentId;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;
}