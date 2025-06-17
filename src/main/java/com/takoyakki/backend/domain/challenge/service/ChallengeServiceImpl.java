package com.takoyakki.backend.domain.challenge.service;

import com.takoyakki.backend.domain.challenge.api.AnthropicClient;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertItemRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemsInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemsSelectResponseDto;
import com.takoyakki.backend.domain.challenge.repository.ProblemSolvingMapper;
import com.takoyakki.backend.domain.challenge.repository.ProblemsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeServiceImpl implements ChallengeService{
    private final ProblemsMapper problemsMapper;
    private final ProblemSolvingMapper problemSolvingMapper;
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

                ProblemsSelectResponseDto responseDto = problemsMapper.selectProblem(problemId);

                ProblemSolvingInsertItemRequestDto item = ProblemSolvingInsertItemRequestDto.builder()
                        .memberId(requestDto.getMemberId())
                        .problemId(problemId)
                        .createdAt(requestDto.getCreatedAt())
                        .answer(answer)
                        .isCorrect(answer == responseDto.getAnswer()? "Y" : "N")
                        .points(answer == responseDto.getAnswer()? responseDto.getPoints() : 0)
                        .build();
                list.add(item);
            }

            return problemSolvingMapper.insertProblemSolving(list);
        } catch (Exception e) {
            throw new RuntimeException("문제 풀이 제출 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
}
