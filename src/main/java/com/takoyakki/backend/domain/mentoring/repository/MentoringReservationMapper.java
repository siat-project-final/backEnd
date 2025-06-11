package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentoringReservationMapper {
    List<MentoringReservation> findByDate(@Param("date") String date);
    void insertReservation(MentoringReservation reservation);
    void deleteReservation(@Param("id") Long id);
   
}