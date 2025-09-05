package com.team.grade_checklist.domain.boxes.repository;

import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjects;
import com.team.grade_checklist.domain.boxes.entity.EnrolledSubjectsId;
import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrolledSubjectsRepository extends JpaRepository<EnrolledSubjects, EnrolledSubjectsId> {
    Optional<EnrolledSubjects> findByStudentIdAndSubjectId(Integer studentId, Integer subjectId);
}
