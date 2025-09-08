package com.team.grade_checklist.domain.credit.entity;


import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjectsId;
import com.team.grade_checklist.domain.subjects.entity.CourseClassification;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="credit_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(CreditStatusId.class)
public class CreditStatus {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_classification_id", nullable = false)
    private CourseClassification courseClassification;

    @Column(name = "required_credits")
    private Integer requiredCredits;

    @Column(name = "earned_credits")
    private Integer earnedCredits;

}
