package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import java.util.List;

public interface MentoringService {
    List<MentoringReservation> getReservationsByDate(String date);
    void createReservation(MentoringReservation reservation);
    void deleteReservation(Long id);
}
