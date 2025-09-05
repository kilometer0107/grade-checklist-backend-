package com.team.grade_checklist.domain.boxes.controller;

import com.team.grade_checklist.domain.boxes.dto.response.BoxResponseDto;
import com.team.grade_checklist.domain.boxes.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/box")
@RequiredArgsConstructor
public class BoxController {
    private final BoxService boxService;

    //TODO : 자신의 목록에 과목 등록
    @PostMapping("/{subjectId}")
    public ResponseEntity<Void> addSubjectToBox(@PathVariable Integer subjectId,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        String currentUserId = userDetails.getUsername();

        boxService.addSubjectToBox(currentUserId, subjectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    //TODO : 등록된 과목 조회
    @GetMapping
    public ResponseEntity<List<BoxResponseDto>> getSubjectsInBox(
            @AuthenticationPrincipal UserDetails userDetails) {

        String currentUserId = userDetails.getUsername();
        List<BoxResponseDto> userBox = boxService.getSubjectsInBox(currentUserId);
        return ResponseEntity.ok(userBox);
    }

    //TODO : 등록된 과목 삭제
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubjectFromBox(
            @PathVariable Integer subjectId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String currentUserId = userDetails.getUsername();
        boxService.deleteSubjectFromBox(currentUserId, subjectId);
        return ResponseEntity.ok().build();
    }

}
