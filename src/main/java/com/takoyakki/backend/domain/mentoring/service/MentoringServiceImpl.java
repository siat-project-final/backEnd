package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentoringReservationMapper mentoringReservationMapper;

    @Override
    public List<MentoringReservation> getReservationsByDate(String date) {
        return mentoringReservationMapper.findByDate(date);
    }

    @Override
    public void createReservation(MentoringReservation reservation) {
        mentoringReservationMapper.insertReservation(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        mentoringReservationMapper.deleteReservation(id);
    }
}
