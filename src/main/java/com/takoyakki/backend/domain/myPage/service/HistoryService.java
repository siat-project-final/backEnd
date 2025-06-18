package com.takoyakki.backend.domain.myPage.service;

import com.takoyakki.backend.domain.challenge.dto.response.ChallengeRankResponseDto;
import com.takoyakki.backend.domain.myPage.dto.response.MyPageMentoringsResponseDto;

import java.util.List;

public interface HistoryService {
    List<ChallengeRankResponseDto> selectChallengeHistory(Long memberId);

    List<MyPageMentoringsResponseDto> selectMentoringHistory(Long memberId);
}
