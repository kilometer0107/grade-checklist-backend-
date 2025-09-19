package com.team.grade_checklist.domain.user.dto.response;

import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.enums.Years;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

//내 정보 조회 응답
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {


    private String studentId;

    private String name;

    private Majors department;

    private Years admissionYear;

    private BigDecimal averageGrade;

    // 비밀번호는 보안상 제외
}