package com.takoyakki.backend.domain.challenge.repository;

import com.takoyakki.backend.domain.challenge.dto.response.ChallengeRankResponseDto;
import com.takoyakki.backend.domain.myPage.dto.response.ChallengeRanksByMemberIdResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DailyChallengeRankingsMapper {
    int insertDailyChallengeRanking(List<ChallengeRankResponseDto> list);

    List<ChallengeRankResponseDto> selectChallengeRanksByMemberId(Long memberId);
}
