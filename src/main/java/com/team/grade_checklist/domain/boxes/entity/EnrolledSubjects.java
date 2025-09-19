package com.team.grade_checklist.domain.boxes.entity;

import com.team.grade_checklist.domain.subjects.entity.CourseClassification;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="enrolled_subjects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(EnrolledSubjectsId.class)
public class EnrolledSubjects {

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User user;

    private boolean isTaken;

}
