package com.takoyakki.backend.domain.challenge.service;

import com.takoyakki.backend.common.exception.ResourceNotFoundException;
import com.takoyakki.backend.domain.challenge.api.AnthropicClient;
import com.takoyakki.backend.domain.challenge.dto.ProblemsInsertRequestDto;
import com.takoyakki.backend.domain.challenge.repository.ProblemsMapper;
import com.takoyakki.backend.domain.dailyLearning.repository.DailyLearningMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeServiceImpl implements ChallengeService{
    private final ProblemsMapper problemsMapper;
    private final AnthropicClient anthropicClient;

    @Override
    public int insertChallengeProblem(String subject, int difficulty) {
        try {
            String problem = anthropicClient.createProblem(subject, difficulty);

            String contents = anthropicClient.extractContents(problem);
            int answer = anthropicClient.extractAnswer(problem);

            ProblemsInsertRequestDto requestDto = ProblemsInsertRequestDto.builder()
                    .title(subject + ": " + difficulty)
                    .contents(contents)
                    .difficulty(difficulty)
                    .subject(subject)
                    .correctAnswer(answer)
                    .build();

            return problemsMapper.insertProblem(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("문제 생성 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
}
