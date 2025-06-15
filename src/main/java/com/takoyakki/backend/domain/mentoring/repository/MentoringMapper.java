package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    // 예약 생성
    void insertReservation(Mentoring mentoring);

    // 멘토링 수정 (날짜, 상태 등)
    void updateMentoring(Mentoring mentoring);

    // 멘토링 삭제
    void deleteMentoring(@Param("mentoringId") Long mentoringId);

    // 전체 멘토링 조회 (페이징)
    List<Mentoring> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 멘토링 상세 조회
    Mentoring selectMentoringById(@Param("id") Long id);

    // 특정 멘토의 멘토링 목록 조회
    List<Mentoring> findMentoringsByMentorId(@Param("mentorId") Long mentorId);

    // 특정 멘토의 예약 목록 조회
    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 예약 취소 (취소 사유 포함)
    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);
}
