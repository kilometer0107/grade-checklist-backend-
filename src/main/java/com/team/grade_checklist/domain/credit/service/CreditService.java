package com.team.grade_checklist.domain.credit.service;

import com.team.grade_checklist.domain.boxes.repository.EnrolledSubjectsRepository;
import com.team.grade_checklist.domain.subjects.repository.SubjectsRepository;
import com.team.grade_checklist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditService {
    private final UserRepository userRepository;
    private final SubjectsRepository subjectsRepository;
    private final EnrolledSubjectsRepository enrolledSubjectsRepository;


}
