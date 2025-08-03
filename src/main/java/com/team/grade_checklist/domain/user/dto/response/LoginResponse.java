package com.team.grade_checklist.domain.user.dto.response;

public record LoginResponse (
        String accessToken,
        String name
) {
}
