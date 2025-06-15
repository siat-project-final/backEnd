package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringReservationMapper {

    // 예약 생성
    void insertReservation(MentoringReservation reservation);

    // 특정 날짜 기준 예약 조회
    List<MentoringReservation> findReservationsByDate(@Param("date") String date);

    //특정 멘티의 예약 목록 조회 (진행 중 / 대기)
    List<MentoringReservation> findReservationsByMenteeId(@Param("menteeId") Long menteeId);

    // 특정 멘티의 지난 예약 내역 조회 (완료된 멘토링)
    List<MentoringReservation> findHistoryReservationsByMenteeId(@Param("menteeId") Long menteeId);

    // 예약 취소 (사유 포함)
    void cancelReservation(@Param("id") Long reservationId, @Param("cancelReason") String cancelReason);



}
