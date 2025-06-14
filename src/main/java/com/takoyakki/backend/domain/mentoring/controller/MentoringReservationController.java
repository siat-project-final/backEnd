package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentoring")
@RequiredArgsConstructor
public class MentoringReservationController {

    private final MentoringService mentoringService;

    //멘토

    // 멘토 리스트 조회 (페이징)
    @GetMapping("/mentors")
    public List<Mentor> getMentorList(@RequestParam(defaultValue = "0") int offset,
                                      @RequestParam(defaultValue = "10") int limit) {
        return mentoringService.getMentorList(offset, limit);
    }

    // 멘토 상세 조회
    @GetMapping("/mentors/{mentorId}")
    public Mentor getMentorDetail(@PathVariable Long mentorId) {
        return mentoringService.getMentorDetail(mentorId);
    }

    //예약

    // 특정 날짜 예약 조회
    @GetMapping("/reservations/{date}")
    public List<MentoringReservation> getReservationsByDate(@PathVariable String date) {
        return mentoringService.getReservationsByDate(date);
    }

    // 멘토링 예약 신청
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody MentoringReservation reservation) {
        mentoringService.createReservation(reservation);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    // 멘토링 예약 삭제 (단순 삭제)
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        mentoringService.deleteReservation(id);
        return ResponseEntity.ok("예약이 삭제되었습니다.");
    }

    // 멘티 기준 내 예약 목록 조회
    @GetMapping("/reservations/mentee/{menteeId}")
    public List<MentoringReservation> getMyReservations(@PathVariable Long menteeId) {
        return mentoringService.getMyReservations(menteeId);
    }

    // 멘토링 예약 취소 (사유 포함)
    @PostMapping("/reservations/{id}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id, @RequestParam String reason) {
        mentoringService.cancelReservation(id, reason);
        return ResponseEntity.ok("예약이 취소되었습니다.");
    }

    // 지난 멘토링 히스토리 조회
    @GetMapping("/reservations/mentee/{menteeId}/history")
    public List<MentoringReservation> getHistoryReservations(@PathVariable Long menteeId) {
        return mentoringService.getHistoryReservations(menteeId);
    }
}
