package com.team.grade_checklist.domain.user.dto.request;

import com.team.grade_checklist.domain.user.enums.Major;

public record SignupRequest (

    String password,
    Integer studentId,
    String name,
    String email,
    Major major

){
}