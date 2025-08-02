package com.team.grade_checklist.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId; //학번(로그인 ID로 사용, 수정 불가}

    @Column(nullable = false)
    private String password; //비번(별도 기능에서 변경)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear; //입학년도: 졸업 요건 버전 판별

    @Enumerated(EnumType.STRING)
    @Column(name = "graduation_track", nullable = false)
    private GraduationTrack graduationTrack; // 졸업트랙: 심화전공, 부전공, 다전공 중 선택


    // 사용자 정보 수정 메서드(학번, 비번 제외)
    public void updateInfo(String name, String department, Integer admissionYear, GraduationTrack graduationTrack) {
        this.name = name;
        this.department = department;
        this.admissionYear = admissionYear;
        this.graduationTrack = graduationTrack;
    }
}
