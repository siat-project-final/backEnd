package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationAcceptResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

//멘토링 성사 이후 기능(멘토링 완료, 조회등)
@Mapper
public interface MentoringMapper {

    // 멘토링 완료
    void completeMentoring(MentoringCompleteRequestDto completeRequestDto);

    // 멘토링 완료된 멘티의 멘토링 내역 조회
    List<MentoringReservationAcceptResponseDto> selectCompletedMentoringsByMenteeId(@Param("menteeId") Long menteeId);

    // 멘토링 완료된 멘토의 멘토링 내역 조회
    List<MentoringReservationAcceptResponseDto> selectCompletedMentoringsByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 완료된 멘토링 내역 조회 (멘토링 ID 기준)
    String selectOpenChatUrlByReservationId(@Param("reservationId") Long reservationId);



}