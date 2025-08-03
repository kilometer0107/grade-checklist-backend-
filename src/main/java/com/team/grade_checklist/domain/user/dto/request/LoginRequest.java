package com.team.grade_checklist.domain.user.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
