package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;

import java.util.List;

public interface MentoringService {

    // 특정 날짜 기준 예약 조회
    List<MentoringReservation> getReservationsByDate(String date);

    // 멘토링 신청
    void createReservation(MentoringReservation reservation);

    // 예약 삭제 (간단 삭제용)
    void deleteReservation(Long id);

    // 멘토 리스트 조회 (페이징 지원)
    List<Mentor> getMentorList(int offset, int limit);

    // 멘토 상세 조회
    Mentor getMentorDetail(Long mentorId);

    // 멘티 기준 본인 예약 목록 조회 (대기/확정 등)
    List<MentoringReservation> getMyReservations(Long menteeId);

    // 멘토링 예약 취소 (사유 포함)
    void cancelReservation(Long reservationId, String cancelReason);

    // 지난 멘토링 히스토리 조회 (멘티 기준)
    List<MentoringReservation> getHistoryReservations(Long menteeId);
}
