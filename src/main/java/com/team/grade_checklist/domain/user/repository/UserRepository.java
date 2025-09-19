package com.team.grade_checklist.domain.user.repository;

import com.team.grade_checklist.domain.user.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByStudentId(String studentId);  //로그인, 사용자 조회 등 사용

    boolean existsByStudentId(String studentId); //회원가입 시 학번 중복 확인용
}
