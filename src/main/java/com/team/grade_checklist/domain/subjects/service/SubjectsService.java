package com.team.grade_checklist.domain.subjects.service;


import com.team.grade_checklist.domain.subjects.dto.response.SubjectsResponseDto;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.enums.Years;
import com.team.grade_checklist.domain.subjects.repository.SubjectsRepository;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectsService {

    private final SubjectsRepository subjectsRepository;
    private final UserRepository userRepository;

    //검색 기능
    public List<SubjectsResponseDto> searchSubjects(String studentId, String query) {

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다."));

        Years admissionYear = user.getAdmissionYear();

        List<Subjects> subjectsList;

        if (query == null || query.trim().isEmpty()) {
            subjectsList = subjectsRepository.findByCurriculumYear(admissionYear);
        } else {
            subjectsList = subjectsRepository.findByCurriculumYearAndSubjectNameContaining(admissionYear, query);
        }

        return subjectsList.stream()
                .map(SubjectsResponseDto::from)
                .collect(Collectors.toList());
    }


}
