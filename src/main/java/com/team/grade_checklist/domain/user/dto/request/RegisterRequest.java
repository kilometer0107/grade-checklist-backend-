package com.team.grade_checklist.domain.user.dto.request;

import com.team.grade_checklist.domain.subjects.enums.Majors;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

//회원가입요청
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "학번은 필수입니다")
    @Pattern(regexp = "^\\d{8,10}$", message = "학번은 8자리 숫자여야 합니다")
    private Integer studentId;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 6, max = 20, message = "비밀번호는 6-20자리여야 합니다")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수입니다")
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수입니다")
    @Size(min = 2, max = 10, message = "이름은 2-10자리여야 합니다")
    private String name;

    @NotBlank(message = "학과는 필수입니다")
    private Majors department;

    @NotNull(message = "입학년도는 필수입니다")
    @Min(value = 2022, message = "입학년도는 2022년 이후여야 합니다")
    @Max(value = 2025, message = "입학년도는 2025년 이전이어야 합니다")
    private Integer admissionYear;
}