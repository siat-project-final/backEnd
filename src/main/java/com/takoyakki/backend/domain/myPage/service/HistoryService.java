package com.takoyakki.backend.domain.myPage.service;

import com.takoyakki.backend.domain.challenge.dto.response.ChallengeRankResponseDto;
import com.takoyakki.backend.domain.myPage.dto.response.ChallengeRanksByMemberIdResponseDto;
import com.takoyakki.backend.domain.myPage.dto.response.MyPageProblemSelectResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface HistoryService {
    List<ChallengeRankResponseDto> selectChallengeHistory(Long memberId);

    List<MyPageProblemSelectResponseDto> selectChallengeDetail(Long memberId, LocalDate date);
}
