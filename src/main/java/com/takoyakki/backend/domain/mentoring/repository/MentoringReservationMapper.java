package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.reservation.MenteeReservationRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationResponseDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.ReservationRejectRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringReservationMapper {

    //예약 등록(멘티신청)
    void insertReservation(MenteeReservationRequestDto requestDto);

    //예약목록조회(멘티기준)
    List<ReservationRejectRequestDto> selectReservationsByMenteeId(MenteeReservationRequestDto requestDto);

    //예약목록조회(멘토기준)
    List<ReservationRejectRequestDto> selectResercationsByMentorID()

    //예약수락처리
    int updateReservationStatus(@Param("reservationId") Long reservationId, @Param("status") String status);

    // 예약 거절 처리 + 사유 저장
    int updateReservationToRejected(@Param("reservationId") Long reservationId,
                                    @Param("reasonCode") String reasonCode,
                                    @Param("reasonMessage") String reasonMessage);

    // 예약 취소 처리 + 사유 저장
    int cancelReservation(@Param("reservationId") Long reservationId,
                          @Param("cancelCode") String cancelCode,
                          @Param("cancelMessage") String cancelMessage);



}
