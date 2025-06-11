package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/mentoring")
@RequiredArgsConstructor
public class MentoringReservationController {

    private final MentoringService mentoringService;

    // 특정 날짜의 일정 조회
    @GetMapping("/reservations/{date}")
    public List<MentoringReservation> getReservationsByDate(@PathVariable String date) {
        return mentoringService.getReservationsByDate(date);
    }

    // 멘토링 예약 추가
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody MentoringReservation reservation) {
        mentoringService.createReservation(reservation);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    // 멘토링 예약 삭제
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        mentoringService.deleteReservation(id);
        return ResponseEntity.ok("예약이 삭제되었습니다.");
    }
}
