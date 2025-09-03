package com.team.grade_checklist.domain.subjects.entity;

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
    @Column(name = "subject_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subjectName;

    private Integer credit;

    private String grade;

    private String semester;

    @Enumerated(EnumType.STRING)
    private

    @ManyToOne
    @JoinColumn(name="")

}
