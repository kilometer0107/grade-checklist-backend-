package com.team.grade_checklist.domain.user.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserUpdateRequest  { //사용자 정보 수정 요청 dto
    private String name;
    private String department;
    private Integer admissionYear;
    private BigDecimal averageGrade;
}
