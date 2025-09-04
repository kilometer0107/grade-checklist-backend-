package com.team.grade_checklist.domain.subjects.repository;

import com.team.grade_checklist.domain.subjects.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
    List<Subjects> findBySubjectNameContainingIgnoreCase(String keyword); // 특정 문자열 포함 검색

}
