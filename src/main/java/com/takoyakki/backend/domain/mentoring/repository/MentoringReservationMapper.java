package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.reservation.MemberReservationRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentoringReservationMapper {

    // 예약 등록 (멘티 신청)
    void insertReservation(MemberReservationRequestDto requestDto);

    // 예약 목록 조회 (멘티 기준)
    List<MentoringReservationResponseDto> selectReservationsByMemberId(@Param("memberId") Long memberId);

    // 예약 목록 조회 (멘토 기준)
    List<MentoringReservationResponseDto> selectReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 예약 수락 처리
    int updateReservationStatus(@Param("reservationId") Long reservationId,
                                @Param("status") String status);

    // 예약 거절 처리 + 사유 저장
    int updateReservationToAccepted(@Param("reservationId") Long reservationId,
                                    @Param("reasonCode") String reasonCode,
                                    @Param("reasonMessage") String reasonMessage);

    // 예약 취소 처리 + 사유 저장
    int cancelReservation(@Param("reservationId") Long reservationId,
                          @Param("cancelReason") String cancelReason);

    // 날짜 기준 예약 조회
    List<MentoringReservationResponseDto> findReservationsByDate(@Param("date") String date);

    // 멘티 기준 예약 전체 조회
    List<MentoringReservationResponseDto> findReservationsByMemberId(@Param("memberId") Long memberId);

    // 멘티 기준 과거 예약 이력 조회
    List<MentoringReservationResponseDto> findHistoryReservationsByMemberId(@Param("memberId") Long memberId);

    int updateReservationToAccepted(Long reservationId);

    int updateReservationToRejected(Long reservationId, String rejectReason);

    int updateReservationToCompleted(Long reservationId);
}
