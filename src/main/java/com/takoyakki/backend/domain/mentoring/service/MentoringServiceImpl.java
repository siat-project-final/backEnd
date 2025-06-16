package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringResponseDto;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentoringMapper mentoringMapper;

    @Override
    public MentoringResponseDto getMentoringById(Long id) {
        return mentoringMapper.selectMentoringResponseById(id);
    }

    @Override
    public List<MentoringResponseDto> getMentoringByMentorId(Long mentorId) {
        return mentoringMapper.selectCompletedMentoringsByMentorId(mentorId);
    }

    @Override
    public List<MentoringResponseDto> getMentoringListByMenteeId(Long menteeId) {
        return mentoringMapper.selectCompletedMentoringsByMenteeId(menteeId);
    }

    @Override
    public void completeMentoring(Long reservationId, MentoringCompleteRequestDto requestDto) {
        // TODO: 예약 상태 'COMPLETE'로 변경, 후기 저장 등
    }
}
