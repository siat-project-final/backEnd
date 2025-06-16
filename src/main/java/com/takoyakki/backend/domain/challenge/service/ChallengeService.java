package com.takoyakki.backend.domain.challenge.service;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import jakarta.validation.Valid;

public interface ChallengeService {
    int insertChallengeProblem(String subject, int difficulty);

    int insertProblemSolving(@Valid ProblemSolvingInsertRequestDto requestDto);
}
