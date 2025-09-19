package com.team.grade_checklist.domain.subjects.repository;

import com.team.grade_checklist.domain.subjects.entity.Subjects;
import com.team.grade_checklist.domain.subjects.enums.Course;
import com.team.grade_checklist.domain.subjects.enums.Majors;
import com.team.grade_checklist.domain.subjects.enums.Years;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
    List<Subjects> findBySubjectNameContainingIgnoreCase(String keyword);// 특정 문자열 포함 검색
    // 1. 개설년도(curriculumYear)로 모든 과목을 찾는 메소드
    List<Subjects> findByCurriculumYear(Years curriculumYear);

    // 2. 개설년도(curriculumYear)가 일치하고, 과목명(subjectName)에 특정 문자열이 포함된 과목을 찾는 메소드
    List<Subjects> findByCurriculumYearAndSubjectNameContaining(Years curriculumYear, String subjectName);

}
