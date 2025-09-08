package com.team.grade_checklist.domain.user.entity;

import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.enums.Years;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    @Enumerated(EnumType.STRING)
    private Majors department;

    @Column(name = "admission_year", nullable = false)
    @Enumerated(EnumType.STRING)
    private Years admissionYear;

    @Column(name = "average_grade", precision = 3, scale = 2) // null 허용
    private BigDecimal averageGrade;

    // 사용자 정보 수정 메서드(학번, 비번 제외)
    public void updateInfo(String name, Majors department, Years admissionYear, BigDecimal averageGrade) {
        this.name = name;
        this.department = department;
        this.admissionYear = admissionYear;
        this.averageGrade = averageGrade;
    }
}
