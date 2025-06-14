package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
//멘토링 신청 관련

@Mapper
public interface MentoringMapper {

    // 신규 멘토링 신청 등록
    void insertMentoring(Mentoring mentoring);

    // 멘토링 수정 (예: 날짜 변경, 상태 변경 등)
    void updateMentoring(Mentoring mentoring);

    // 예약 완전 삭제 (필요 시)- 취소/ 삭제 둘중에 1택
    void deleteMentoring(@Param("mentoringId") Long mentoringId);

    // 모든 멘토링 조회( 필요시 페이지 추가)
    List<Mentoring> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID기준 상세 멘토링 조회
    Mentoring selectMentoringById(@Param("id") Long id);

    // 멘토기준 예약 목록 조회
    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 예약취소처리 (취소 사유 포함)
    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);

}

    
