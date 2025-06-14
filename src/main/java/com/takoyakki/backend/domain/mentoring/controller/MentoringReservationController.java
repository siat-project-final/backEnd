// src/main/java/com/takoyakki/backend/domain/mentoring/controller/MentoringReservationController.java
package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//예약 조회, 예약 취소 등 담당 .
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

    // 멘토링 예약 취소
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        mentoringService.deleteReservation(id);
        return ResponseEntity.ok("예약이 삭제되었습니다.");
    }
}