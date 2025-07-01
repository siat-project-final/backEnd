package com.takoyakki.backend.domain.challenge.repository;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertItemRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemsInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.response.ChallengeRankResponseDto;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemSolvingResultResponseDto;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemsSelectResponseDto;
import com.takoyakki.backend.domain.myPage.dto.response.MyPageProblemSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProblemSolvingMapper {
    int insertProblemSolving(List<ProblemSolvingInsertItemRequestDto> requestDto);

    List<ChallengeRankResponseDto> calculateChallengeRank(LocalDate date);

    List<MyPageProblemSelectResponseDto> selectChallengeDetail(Long memberId, LocalDate date);

    List<ProblemsSelectResponseDto> selectChallengeReviewProblem(Long memberId, String subject);

    int countSubmissionsByMemberAndDate(@Param("memberId") Long memberId, @Param("date") LocalDate date);

    List<ProblemSolvingResultResponseDto> selectScoringDetailByMemberAndDate(
        @Param("memberId") Long memberId,
        @Param("date") LocalDate date
    );
}
