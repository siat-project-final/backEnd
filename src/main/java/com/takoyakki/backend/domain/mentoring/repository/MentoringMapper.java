package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    void insertMentoring(Mentoring mentoring);
    void updateMentoring(Mentoring mentoring);
    void deleteMentoring(@Param("mentoringId") Long mentoringId);

    List<Mentoring> findAllMentorings();

    Mentoring selectMentoringById(@Param("id") Long id);
    // 멘토의 예약 목록 조회
    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 예약 취소
    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);


}
    
