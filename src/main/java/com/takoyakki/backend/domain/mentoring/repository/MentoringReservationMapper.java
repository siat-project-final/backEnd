package com.takoyakki.backend.domain.mentoring.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MentoringReservationMapper {
    List<MentoringReservation> findByDate(@Param("date") String date);
    void insertReservation(MentoringReservation reservation);
    void deleteReservation(@Param("id") Long id);
}
