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

    List<Mentoring> findReservationsByMentorId(@Param("mentorId") Long mentorId);


    void cancelReservation(@Param("id") Long id, @Param("cancelReason") String cancelReason);


}
    
