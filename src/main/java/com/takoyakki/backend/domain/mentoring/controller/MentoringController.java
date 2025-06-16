package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationCancelRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationAcceptResponseDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationResponseDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MenteeReservationRequestDto;
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

    // 멘토조회

    // 멘토 리스트 조회 (페이징 적용: offset, limit)
    @GetMapping("/mentors")
    public List<MentoringReservationAcceptResponseDto> getMentorList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return mentoringService.getMentorList(offset, limit);
    }

    // 멘토 상세 조회 (멘토 ID 기준)
    @GetMapping("/mentors/{mentorId}")
    public MentoringReservationAcceptResponseDto getMentorDetail(@PathVariable Long mentorId) {
        return mentoringService.getMentorDetail(mentorId);
    }

    //사전대화

    // 사전 대화 작성 페이지 초기 데이터 조회 (멘토 상세 + 대화 주제 리스트)
    @GetMapping("/preConversation/init/{mentorId}")
    public MenteeReservationRequestDto getPreConversationInitData(@PathVariable Long mentorId) {
        return mentoringService.getPreConversationData(mentorId);
    }

    //사전대화 작성 및 멘토링 대화제출
    @PostMapping("/preConversation/submit")
    public ResponseEntity<String> submitPreConversation(@RequestBody MentoringCompleteRequestDto request) {
        mentoringService.applyMentoring(request);
        return ResponseEntity.ok("멘토링 신청이 완료되었습니다.");
    }

    //예약

    //날짜 별 예약 조회
    @GetMapping("/reservations/{date}")
    public List<MentoringReservationResponseDto> getReservationsByDate(@PathVariable String date) {
        return mentoringService.getReservationsByDate(date);
    }

    //멘토링 예약 신청
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody MentoringReservationResponseDto reservation) {
        mentoringService.createReservation(reservation);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    //멘티 예약 목록 조회
    @GetMapping("/reservations/mentee/{menteeId}")
    public List<MentoringReservationResponseDto> getMyReservations(@PathVariable Long menteeId) {
        return mentoringService.getMyReservations(menteeId);
    }


    // 예약 취소 (사유 포함) - DTO로 받도록 수정
    @PostMapping("/reservations/{id}/cancel")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long id,
            @RequestBody MentoringReservationCancelRequestDto reservationcancelRequestDtoMentoring) {

        // PathVariable과 DTO의 id가 일치하는지 체크
        if (!id.equals(reservationcancelRequestDtoMentoring.getReservationId())) {
            return ResponseEntity.badRequest().body("예약 ID가 일치하지 않습니다.");
        }

        mentoringService.cancelReservation(id, reservationcancelRequestDtoMentoring.getCancel_reason());
        return ResponseEntity.ok("예약이 취소되었습니다.");
    }

    //지난 멘토링 히스토리 조회
    @GetMapping("/reservations/mentee/{menteeId}/history")
    public List<MentoringReservationResponseDto> getHistoryReservations(@PathVariable Long menteeId) {
        return mentoringService.getHistoryReservations(menteeId);
    }

    // 전체 멘토링 리스트 조회 (페이징)
    @GetMapping("/all")
    public List<MentoringCompleteRequestDto> getAllMentoring(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return mentoringService.findAllMentoring(offset, limit);
    }

    // 특정 멘토의 멘토링 목록 조회
    @GetMapping("/mentor/{mentorId}/mentoring-list")
    public List<MentoringCompleteRequestDto> getMentorMentoringList(@PathVariable Long mentorId) {
        return mentoringService.getMentoringByMentorId(mentorId);
    }

    // 멘토링 정보 수정 (PUT)
    @PutMapping("/mentoring/{id}")
    public ResponseEntity<String> updateMentoring(@PathVariable Long id,
                                                  @RequestBody MentoringCompleteRequestDto mentoringCompleteRequestDto) {
        mentoringCompleteRequestDto.setId(id);
        mentoringService.updateMentoring(mentoringCompleteRequestDto);
        return ResponseEntity.ok("멘토링 정보가 수정되었습니다.");
    }

}