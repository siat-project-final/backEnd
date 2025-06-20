package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.dto.reservation.*;
import com.takoyakki.backend.domain.mentoring.service.MentoringReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class MentoringReservationController {

    private final MentoringReservationService reservationService;

    // 멘티 - 멘토링 예약 생성
    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody MemberReservationRequestDto requestDto) {
        reservationService.createReservation(requestDto);
        return ResponseEntity.ok().build();
    }

    // 멘티 - 본인 예약 목록 조회
    @GetMapping("/mentee/{memberId}")
    public ResponseEntity<List<MentoringReservationResponseDto>> getMemberReservations(@PathVariable Long memberId) {
        List<MentoringReservationResponseDto> reservations = reservationService.getReservationsByMemberId(memberId);
        return ResponseEntity.ok(reservations);
    }

    //멘토 - 본인 예약 목록 조회
    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<MentoringReservationResponseDto>> getMentorReservations(@PathVariable Long mentorId) {
        List<MentoringReservationResponseDto> reservations = reservationService.getReservationsByMentorId(mentorId);
        return ResponseEntity.ok(reservations);
    }

    // 멘토 - 예약 수락
    @PutMapping("/mentor/{reservationId}/accept")
    public ResponseEntity<Void> acceptReservation(@PathVariable Long reservationId) {
        reservationService.acceptReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    //멘토 - 예약 거절
    @PutMapping("/mentor/{reservationId}/reject")
    public ResponseEntity<Void> rejectReservation(
            @PathVariable Long reservationId,
            @RequestBody MentoringReservationRejectRequestDto decisionDto
    ) {
        reservationService.rejectReservation(reservationId, decisionDto);
        return ResponseEntity.ok().build();
    }

    //멘티 - 예약 취소
    @PutMapping("/mentee/{reservationId}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long reservationId,
            @RequestBody MentoringReservationCancelRequestDto cancelDto
    ) {
        reservationService.cancelReservation(reservationId, cancelDto);
        return ResponseEntity.ok().build();
    }
}
