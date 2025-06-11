package com.takoyakki.backend.domain.mentoring.service;

public interface MentoringService {
    List<MentoringReservation> getReservationsByDate(String date);
    void createReservation(MentoringReservation reservation);
    void deleteReservation(Long id);
}
