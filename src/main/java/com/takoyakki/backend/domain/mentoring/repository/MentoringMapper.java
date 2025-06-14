package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {


    // 신규 멘토링 신청 등록
    void insertMentoring(Mentoring mentoring);


    // 멘토링 수정 (예: 날짜 변경, 상태 변경 등)
    void updateMentoring(Mentoring mentoring);

    // 예약 완전 삭제 (필요 시)
    void deleteMentoring(@Param("mentoringId") Long mentoringId);

    // 모든 멘토링 조회 (페이징 추가 권장)
    List<Mentoring> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 상세 멘토링 조회
    Mentoring selectMentoringById(@Param("id") Long id);

    // 멘토 기준 예약 목록 조회
    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 예약 취소 처리 (취소 사유 포함)
    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);

}

    
