package com.team.grade_checklist.domain.user.dto.request;

public record SignupRequest (

    String password,
    String studentId,
    String name
){
}
