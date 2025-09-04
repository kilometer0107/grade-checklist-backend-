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
    @Column(name = "subjects_id", nullable = false)
    @GeneratedValue
    private Integer subjectId;

    @ManyToOne
    @JoinColumn(name = "course_classification_id", nullable = false)
    private Integer courseId;

    private String subjectName;

    private Integer credit;

    private String grade;

    private String semester;

    private Majors major;

    private Years year;
}
