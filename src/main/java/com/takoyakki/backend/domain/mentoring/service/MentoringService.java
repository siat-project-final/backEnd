package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringResponseDto;

import java.util.List;

public interface MentoringService {

    // 멘토링 완료 처리
    void completeMentoring(Long reservationId, MentoringCompleteRequestDto requestDto);

    // 멘티가 완료한 멘토링 목록 조회
    List<MentoringResponseDto> getMentoringListByMenteeId(Long menteeId);

    // 멘토가 완료한 멘토링 목록 조회
    List<MentoringResponseDto> getMentoringByMentorId(Long mentorId);

    // 특정 멘토링 상세 조회
    MentoringResponseDto getMentoringById(Long mentoringId);
}
