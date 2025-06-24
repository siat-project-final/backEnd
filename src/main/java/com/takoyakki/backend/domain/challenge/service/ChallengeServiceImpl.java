package com.takoyakki.backend.domain.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takoyakki.backend.domain.challenge.api.AnthropicClient;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertItemRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemsInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.response.*;
import com.takoyakki.backend.domain.challenge.repository.DailyChallengeRankingsMapper;
import com.takoyakki.backend.domain.challenge.repository.ProblemSolvingMapper;
import com.takoyakki.backend.domain.challenge.repository.ProblemsMapper;
import com.takoyakki.backend.domain.dailyLearning.repository.DailyLearningMapper;
import com.takoyakki.backend.domain.myPage.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ChallengeServiceImpl implements ChallengeService{
    private final ProblemsMapper problemsMapper;
    private final ProblemSolvingMapper problemSolvingMapper;

    private final DailyChallengeRankingsMapper dailyChallengeRankingsMapper;
    private final DailyLearningMapper dailyLearningMapper;

    private final MemberMapper memberMapper;


    private final AnthropicClient anthropicClient;

    // 챌린지 메인

    @Override
    public List<ProblemsSelectResponseDto> selectChallengeProblems() {
        try {
            return problemsMapper.selectChallengeProblems();
        } catch (Exception e) {
            throw new RuntimeException("문제 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProblemSolvingResultResponseDto> selectProblemSolvingResult(Long memberId) {
        return problemsMapper.selectProblemSolvingResult(memberId);
    }


    @Override
    public int insertChallengeProblem(String subject, int difficulty) {
        try {
            String problem = anthropicClient.createProblem(subject, difficulty);

            String contents = anthropicClient.extractContents(problem);
            int answer = anthropicClient.extractAnswer(problem);

            String title = anthropicClient.extractTitle(contents);
            List<String> choices = anthropicClient.extractChoice(contents);

            ProblemsInsertRequestDto requestDto = ProblemsInsertRequestDto.builder()
                    .title(title)
                    .contents(contents)
                    .difficulty(difficulty)
                    .subject(subject)
                    .correctAnswer(answer)
                    .choices(choices)
                    .build();

            return problemsMapper.insertProblem(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("문제 생성 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public int insertProblemSolving(ProblemSolvingInsertRequestDto requestDto) {
        try {
            List<Long> problemIds = requestDto.getProblemIds();
            List<Integer> answers = requestDto.getAnswers();

            // Entity 리스트 생성
            List<ProblemSolvingInsertItemRequestDto> list = new ArrayList<>();
            for (int i = 0; i < problemIds.size(); i++) {
                Long problemId = problemIds.get(i);
                Integer answer = answers.get(i);

                ProblemSolvingSubmitResponseDto responseDto = problemsMapper.selectProblem(problemId);

                ProblemSolvingInsertItemRequestDto item = ProblemSolvingInsertItemRequestDto.builder()
                        .memberId(requestDto.getMemberId())
                        .problemId(problemId)
                        .createdAt(requestDto.getCreatedAt())
                        .answer(answer)
                        .isCorrect(answer == responseDto.getAnswer()? "Y" : "N")
                        .points(answer == responseDto.getAnswer()? responseDto.getDifficulty() : 0)
                        .build();
                list.add(item);
            }

            return problemSolvingMapper.insertProblemSolving(list);
        } catch (Exception e) {
            throw new RuntimeException("문제 풀이 제출 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ChallengeRankResponseDto> selectChallengeRankByDate(LocalDate date) {
        try {
            return problemSolvingMapper.calculateChallengeRank(date);
        } catch (Exception e) {
            throw new RuntimeException("랭킹 조회 중 문제가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public int insertDailyChallengeRanking(List<ChallengeRankResponseDto> challengeRankResponseDtos) {
        try {
            return dailyChallengeRankingsMapper.insertDailyChallengeRanking(challengeRankResponseDtos);
        } catch (Exception e) {
            throw new RuntimeException("랭킹 데이터 삽입 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public int getPointsByDailyChallengeRank(Long memberId, int rank) {
        try {
            int bonusPoints = 0;
            switch (rank) {
                case 1:
                    bonusPoints = 30;
                    break;
                case 2:
                    bonusPoints = 20;
                    break;
                case 3:
                    bonusPoints = 10;
                    break;
                default:
                    return 0;
            }

            log.info("포인트 지급 완료 - memberId: {}, rank: {}, 지급 포인트: {}",
                    memberId, rank, bonusPoints);

            return memberMapper.getPointsByDailyChallengeRank(memberId, bonusPoints);
        } catch (Exception e) {
            throw new RuntimeException("챌린지 포인트 지급 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    // 챌린지 복습

    @Override
    public List<ChallengeReviewSelectResponseDto> selectChallengeReviewList() {
        try {
            return dailyLearningMapper.selectDailyLearningProgress();
        } catch (Exception e) {
            throw new RuntimeException("챌린지 전체 문제 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public ProblemsSelectResponseDto selectChallengeReviewProblem(Long memberId, String subject) {
        try {
            List<ProblemsSelectResponseDto> list = problemSolvingMapper.selectChallengeReviewProblem(memberId, subject);
            int randomIndex = ThreadLocalRandom.current().nextInt(list.size());
            return list.stream()
                    .skip(randomIndex)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("랜덤 요소를 찾을 수 없습니다."));
        } catch (IllegalStateException e) {
            throw new RuntimeException("복습 문제 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }


}
