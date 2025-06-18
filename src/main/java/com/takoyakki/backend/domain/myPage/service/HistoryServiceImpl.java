package com.takoyakki.backend.domain.myPage.service;

import com.takoyakki.backend.domain.challenge.dto.response.ChallengeRankResponseDto;
import com.takoyakki.backend.domain.challenge.repository.DailyChallengeRankingsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService{
    private final DailyChallengeRankingsMapper dailyChallengeRankingsMapper;
    @Override
    public List<ChallengeRankResponseDto> selectChallengeHistory(Long memberId) {
        return dailyChallengeRankingsMapper.selectChallengeRanksByMemberId(memberId);
    }
}
