package com.team.grade_checklist.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//내 정보 조회 응답
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {


    private String studentId;

    private String name;

    private String department;

    private Integer admissionYear;

    // 비밀번호는 보안상 제외
}