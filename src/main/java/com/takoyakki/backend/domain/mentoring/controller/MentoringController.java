package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringResponseDto;
import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentoring")
@Validated
public class MentoringController {

    private final MentoringService mentoringService;

    // 🔹 멘토 - 멘토링 완료 처리 (오픈채팅 링크 자동 포함)
    @PostMapping("/{reservationId}/complete")
    public ResponseEntity<Void> completeMentoring(
            @PathVariable Long reservationId,
             @RequestBody MentoringCompleteRequestDto requestDto
    ) {
        mentoringService.completeMentoring(reservationId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 🔹 멘티 - 완료된 멘토링 목록 조회
    @GetMapping("/mentee/{menteeId}")
    public ResponseEntity<List<MentoringResponseDto>> getMentoringForMentee(@PathVariable Long menteeId) {
        return ResponseEntity.ok(mentoringService.getMentoringListByMenteeId(menteeId));
    }

    // 🔹 멘토 - 완료된 멘토링 목록 조회
    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<MentoringResponseDto>> getMentoringForMentor(@PathVariable Long mentorId) {
        return ResponseEntity.ok(mentoringService.getMentoringListByMentorId(mentorId));
    }

    // 🔹 특정 멘토링 조회
    @GetMapping("/{mentoringId}")
    public ResponseEntity<MentoringResponseDto> getMentoringById(@PathVariable Long mentoringId) {
        return ResponseEntity.ok(mentoringService.getMentoringById(mentoringId));
    }
}
