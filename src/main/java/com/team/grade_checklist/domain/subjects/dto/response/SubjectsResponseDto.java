package com.team.grade_checklist.domain.subjects.dto.response;

import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.enums.Majors;

public record SubjectsResponseDto(
        Integer subjectId,
        String subjectName,
        Integer credit,
        String semester,
        Majors majors
) {
    // Entity를 Record DTO로 변환하는 정적 팩토리 메서드 (선택사항이지만 편리함)
    public static SubjectsResponseDto from(Subjects subject) {
        return new SubjectsResponseDto(
                subject.getSubjectId(),
                subject.getSubjectName(),
                subject.getCredit(),
                subject.getSemester(),
                subject.getMajor()
        );
    }
}
