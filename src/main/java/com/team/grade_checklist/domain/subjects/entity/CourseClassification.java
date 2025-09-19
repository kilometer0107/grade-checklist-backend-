package com.team.grade_checklist.domain.subjects.entity;


import com.team.grade_checklist.domain.subjects.enums.Course;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="course_classification")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseClassification {
    @Id
    @Column(name = "course_classification_id", nullable = false)
    @GeneratedValue
    private Integer courseClassificationId;

    @Enumerated(EnumType.STRING)
    private Course course;
}
