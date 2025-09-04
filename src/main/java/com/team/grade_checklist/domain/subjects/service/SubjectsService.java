package com.team.grade_checklist.domain.subjects.service;


import com.team.grade_checklist.domain.subjects.dto.response.SubjectsResponseDto;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.repository.SubjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectsService {

    private final SubjectsRepository subjectsRepository;

    //검색 기능
    public List<SubjectsResponseDto> searchSubjects(String query) {
        List<Subjects> subjectsList;
        if (query == null || query.trim().isEmpty()) {
            subjectsList = subjectsRepository.findAll();
        } else {
            subjectsList = subjectsRepository.findBySubjectNameContainingIgnoreCase(query);
        }

        return subjectsList.stream()
                .map(SubjectsResponseDto::from)
                .collect(Collectors.toList());
    }


}
