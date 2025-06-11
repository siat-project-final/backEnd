package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService{

    private final MentorMapper mentorMapper;
    private final MentoringMapper mentoringMapper;
    private final MentoringReservationMapper mentoringReservationMapper;
}
