package com.team.grade_checklist.domain.user.entity;

import com.team.grade_checklist.domain.user.enums.GraduationTrack;
import jakarta.persistence.*;
import lombok.*;

//사용자 엔티티
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "graduation_track", nullable = false)
    private GraduationTrack graduationTrack;

    public void updateInfo(String name, String department, Integer admissionYear, GraduationTrack graduationTrack) {
        this.name = name;
        this.department = department;
        this.admissionYear = admissionYear;
        this.graduationTrack = graduationTrack;
    }
}
