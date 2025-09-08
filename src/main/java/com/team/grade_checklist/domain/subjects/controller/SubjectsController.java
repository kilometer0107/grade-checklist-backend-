package com.team.grade_checklist.domain.subjects.controller;


import com.team.grade_checklist.domain.subjects.dto.response.SubjectsResponseDto;
import com.team.grade_checklist.domain.subjects.service.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectsController {
    private final SubjectsService subjectsService;

    // 과목 전체 조회 및 검색
    @GetMapping
    public ResponseEntity<List<SubjectsResponseDto>> searchSubjects(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "query", required = false) String query) {

        String studentId = userDetails.getUsername();

        List<SubjectsResponseDto> results = subjectsService.searchSubjects(studentId, query);
        return ResponseEntity.ok(results);
    }


}
