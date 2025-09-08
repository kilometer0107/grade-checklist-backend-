package com.team.grade_checklist.domain.subjects.entity;

import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.enums.Years;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="subjects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @ManyToOne
    @JoinColumn(name = "course_classification_id", nullable = false)
    private CourseClassification courseId;

    private String subjectName;

    private Integer credit;

    private String grade;

    private String semester;

    @Enumerated(EnumType.STRING)
    private Majors major;

    @Enumerated(EnumType.STRING)
    private Years curriculumYear;
}
