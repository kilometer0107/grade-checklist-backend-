package com.team.grade_checklist.domain.subjects.controller;


import com.team.grade_checklist.domain.subjects.service.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/subjects")
@RequiredArgsConstructor
public class SubjectsController {
    private final SubjectsService subjectsService;

    // 등록 과목 조회
    @GetMapping
    
}
