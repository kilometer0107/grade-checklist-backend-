package com.team.grade_checklist.domain.boxes.dto.response;

import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjects;
import com.team.grade_checklist.domain.subjects.entity.Subjects;

public record BoxResponseDto(
        // Subjects 엔티티에서 가져올 정보
        Integer subjectId,
        String subjectName,
        Integer credit,

        // EnrolledSubjects 엔티티에서 가져올 정보
        boolean isTaken
) {
    public static BoxResponseDto from(EnrolledSubjects enrolled) {
        // EnrolledSubjects 엔티티 안에 있는 Subjects 엔티티
        Subjects subject = enrolled.getSubject();

        return new BoxResponseDto(
                subject.getSubjectId(),
                subject.getSubjectName(),
                subject.getCredit(),
                enrolled.isTaken() // 수강 여부는 EnrolledSubjects에서
        );
    }
}
