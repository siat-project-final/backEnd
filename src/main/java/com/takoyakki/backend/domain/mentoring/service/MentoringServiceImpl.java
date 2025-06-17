package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringResponseDto;
import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;

import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentoringMapper mentoringMapper;
    private final MentoringReservationMapper mentoringReservationMapper;

    @Override
    public void completeMentoring(Long reservationId, MentoringCompleteRequestDto requestDto) {
        // 예약 ID를 Dto에 세팅하거나 requestDto 내부에서 갖고 있다고 가정
        requestDto.setMentoringReservationId(reservationId);
        mentoringMapper.completeMentoring(requestDto);

        // 상태값 'COMPLETE'로 업데이트
        mentoringReservationMapper.updateReservationToCompleted(reservationId);

        //후기 저장 기능 추가 시, 여기에서 후기 저장 호출
    }

    @Override
    public void updateMentoringStatus(Long reservationId, String status) {
        mentoringMapper.updateMentoringStatus(reservationId, status);
    }

    @Override
    public MentoringResponseDto getMentoringById(Long id) {
        return mentoringMapper.selectMentoringResponseById(id);
    }

    @Override
    public List<MentoringResponseDto> getMentoringListByMenteeId(Long menteeId) {
        return mentoringMapper.selectCompletedMentoringsByMenteeId(menteeId);
    }

    @Override
    public List<MentoringResponseDto> getMentoringListByMentorId(Long mentorId) {
        return mentoringMapper.selectCompletedMentoringsByMentorId(mentorId);
    }

    @Override
    public String getOpenChatUrlByReservationId(Long reservationId) {
        return mentoringMapper.selectOpenChatUrlByReservationId(reservationId);
    }
}
