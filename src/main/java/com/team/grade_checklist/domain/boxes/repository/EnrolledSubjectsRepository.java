package com.team.grade_checklist.domain.boxes.repository;

import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjects;
import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjectsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrolledSubjectsRepository extends JpaRepository<EnrolledSubjects, EnrolledSubjectsId> {
    Optional<EnrolledSubjects> findByUser_StudentIdAndSubject_SubjectId(String studentId, Integer subjectId);
    List<EnrolledSubjects> findAllByUser_StudentId(String studentId);
    List<EnrolledSubjects> findByUser_StudentIdAndIsTakenTrue(String studentId);// 체크한 과목만 가져오는 기능
}
