package com.team.grade_checklist.domain.boxes.service;


import com.team.grade_checklist.domain.boxes.dto.response.BoxResponseDto;
import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjects;
import com.team.grade_checklist.domain.boxes.repository.EnrolledSubjectsRepository;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.repository.SubjectsRepository;
import com.team.grade_checklist.domain.user.entity.User;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoxService {

    //사용자의 체크리스트에 과목 추가 (등록)
    private final EnrolledSubjectsRepository enrolledSubjectsRepository;
    private final SubjectsRepository subjectsRepository;
    private final UserRepository userRepository;

    public void addSubjectToBox(String studentId, Integer subjectId) {
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        Subjects subjects = subjectsRepository.findById(subjectId)
                .orElseThrow(()->new IllegalArgumentException("과목을 찾을 수 없습니다"));

        EnrolledSubjects enrolledSubject = EnrolledSubjects.builder()
                .user(user)
                .subject(subjects)
                .isTaken(false)
                .build();
        enrolledSubjectsRepository.save(enrolledSubject);
    }

    public void deleteSubjectFromBox(String studentId, Integer subjectId) {
        //pkfk로 enrolledsubjects 엔티티 찾기
        EnrolledSubjects enrolledSubject = enrolledSubjectsRepository.findByUser_StudentIdAndSubject_SubjectId(studentId, subjectId)
                .orElseThrow(()-> new IllegalArgumentException("해당 과목이 등록되어있지 않습니다"));
        enrolledSubjectsRepository.delete(enrolledSubject);
    }

    @Transactional(readOnly = true)
    public List<BoxResponseDto> getSubjectsInBox(String studentId) {
        List<EnrolledSubjects> enrolledList = enrolledSubjectsRepository.findAllByUser_StudentId(studentId);

        return enrolledList.stream()
                .map(BoxResponseDto::from) // EnrolledSubjects 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }
}
