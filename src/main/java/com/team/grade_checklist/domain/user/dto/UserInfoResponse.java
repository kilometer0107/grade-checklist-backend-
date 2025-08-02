package com.team.grade_checklist.domain.user.dto;

import com.team.grade_checklist.domain.user.GraduationTrack;
import com.team.grade_checklist.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse { //사용자 정보 조회 응답 dto
    private String studentId;
    private String name;
    private String department;
    private Integer admissionYear;
    private GraduationTrack graduationTrack;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .studentId(user.getStudentId())
                .name(user.getName())
                .department(user.getDepartment())
                .admissionYear(user.getAdmissionYear())
                .graduationTrack(user.getGraduationTrack())
                .build();
    }
}
