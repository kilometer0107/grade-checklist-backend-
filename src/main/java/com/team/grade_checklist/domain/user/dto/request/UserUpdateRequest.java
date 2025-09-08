package com.team.grade_checklist.domain.user.dto.request;

import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.enums.Years;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserUpdateRequest  { //사용자 정보 수정 요청 dto
    private String name;
    private Majors department;
    private Years admissionYear;
    private BigDecimal averageGrade;
}
