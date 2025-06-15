package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringReservationDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;
import com.takoyakki.backend.domain.mentoring.model.Mentor;

import java.util.List;

public interface MentoringService {

    // 특정 날짜 기준 예약 조회
    List<MentoringReservationDto> getReservationsByDate(String date);

    // 멘토링 예약 신청
    void createReservation(MentoringReservationDto reservationDto);

    // 멘토 리스트 조회 (페이징 지원)
    List<Mentor> getMentorList(int offset, int limit);

    // 멘토 상세 조회
    Mentor getMentorDetail(Long mentorId);

    // 멘티 기준 본인 예약 목록 조회 (대기/확정 등)
    List<MentoringReservationDto> getMyReservations(Long menteeId);

    // 멘토링 예약 취소 (취소 사유 포함)
    void cancelReservation(Long reservationId, String cancelReason);

    // 지난 멘토링 히스토리 조회
    List<MentoringReservationDto> getHistoryReservations(Long menteeId);

    //사전대화

    // 사전 대화 작성 페이지 초기 데이터 조회 (멘토 상세 + 선택 가능한 대화 주제 리스트)
    PreConversationDto getPreConversationData(Long mentorId);

    // 사전 대화 작성 및 멘토링 신청 처리
    void applyMentoring(MentoringRequestDto requestDto);
}
