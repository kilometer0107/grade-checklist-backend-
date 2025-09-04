package com.team.grade_checklist.domain.subjects.dto.request;

public record SubjectsRequestDto (
        Integer courseClassificationId,
        String subjectName,
        Integer credit
){
}
