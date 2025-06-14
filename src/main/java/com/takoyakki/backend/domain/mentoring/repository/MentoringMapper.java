package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    //멘토링 기본

    // 멘토링생성
    void insertReservation(Mentoring mentoring);
    // 멘토링수정(날짜, 상태 변경 등)
    void updateMentoring(Mentoring mentoring);

    // 멘토링삭제(필요 시)
    void deleteMentoring(@Param("mentoringId") Long mentoringId);


    //조회

    // 전체 멘토링 조회 (페이징 처리 권장)
    List<Mentoring> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 멘토링 상세 조회
    Mentoring selectMentoringById(@Param("id") Long id);

    // 특정 멘토가 진행하는 멘토링 목록 조회
    List<Mentoring> findMentoringsByMentorId(@Param("mentorId") Long mentorId);


    //예약

    // 특정멘토의 예약 목록 조회
    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 예약 취소 처리 (취소 사유 포함)
    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);

}
