package com.takoyakki.backend.domain.mentoring.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MentorMapper {
    
    
    List<Mentor> findAllMentors();

    Mentor findMentorById(@Param("id") Long id);

    void insertMentor(Mentor mentor);

    void insertAvailableDates(@Param("mentorId") Long mentorId, @Param("dates") List<String> dates);
}
