package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;
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
public class MentoringController {

    private final MentoringService mentoringService;

    // ========== 멘토 조회 관련 ==========

    /** 멘토 리스트 조회 (페이징 적용: offset, limit) */
    @GetMapping("/mentors")
    public List<Mentor> getMentorList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return mentoringService.getMentorList(offset, limit);
    }

    /** 멘토 상세 조회 (멘토 ID 기준) */
    @GetMapping("/mentors/{mentorId}")
    public Mentor getMentorDetail(@PathVariable Long mentorId) {
        return mentoringService.getMentorDetail(mentorId);
    }

    // ========== 사전 대화 작성 관련 ==========

    /** 사전 대화 작성 페이지 초기 데이터 조회 (멘토 상세 + 대화 주제 리스트) */
    @GetMapping("/preconversation/init/{mentorId}")
    public PreConversationDto getPreConversationInitData(@PathVariable Long mentorId) {
        return mentoringService.getPreConversationData(mentorId);
    }

    /** 사전 대화 작성 및 멘토링 신청 제출 */
    @PostMapping("/preconversation/submit")
    public ResponseEntity<String> submitPreConversation(@RequestBody MentoringRequestDto request) {
        mentoringService.applyMentoring(request);
        return ResponseEntity.ok("멘토링 신청이 완료되었습니다.");
    }

    // ========== 예약 관련 ==========

    /** 특정 날짜 기준 예약 조회 */
    @GetMapping("/reservations/{date}")
    public List<MentoringReservation> getReservationsByDate(@PathVariable String date) {
        return mentoringService.getReservationsByDate(date);
    }

    /** 멘토링 예약 신청 */
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody MentoringReservation reservation) {
        mentoringService.createReservation(reservation);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    /** 멘티 기준 내 예약 목록 조회 */
    @GetMapping("/reservations/mentee/{menteeId}")
    public List<MentoringReservation> getMyReservations(@PathVariable Long menteeId) {
        return mentoringService.getMyReservations(menteeId);
    }

    /** 예약 취소 (취소 사유 필수) */
    @PostMapping("/reservations/{id}/cancel")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long id,
            @RequestParam String reason) {
        mentoringService.cancelReservation(id, reason);
        return ResponseEntity.ok("예약이 취소되었습니다.");
    }

    /** 지난 멘토링 히스토리 조회 */
    @GetMapping("/reservations/mentee/{menteeId}/history")
    public List<MentoringReservation> getHistoryReservations(@PathVariable Long menteeId) {
        return mentoringService.getHistoryReservations(menteeId);
    }
}
