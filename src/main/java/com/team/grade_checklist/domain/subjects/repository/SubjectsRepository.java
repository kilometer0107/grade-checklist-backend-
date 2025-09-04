package com.team.grade_checklist.domain.subjects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface SubjectsRepository extends JpaRepository<Book, Long> {
}
