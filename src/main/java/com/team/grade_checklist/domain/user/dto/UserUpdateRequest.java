package com.team.grade_checklist.domain.user.dto;

import com.team.grade_checklist.domain.user.GraduationTrack;
import lombok.Getter;

@Getter
public class UserUpdateRequest { //사용자 정보 수정 요청 dto
    private String name;
    private String department;
    private Integer admissionYear;
    private GraduationTrack graduationTrack;
}
