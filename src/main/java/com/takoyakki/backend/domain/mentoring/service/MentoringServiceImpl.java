package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import org.springframework.http.ResponseEntity;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import org.springframework.web.bind.annotation.GetMapping;

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
