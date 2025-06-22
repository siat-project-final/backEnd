package com.takoyakki.backend.domain.challenge.controller;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.response.ChallengeReviewSelectResponseDto;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemsSelectResponseDto;
import com.takoyakki.backend.domain.challenge.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/challenge")
@Tag(name = "AI 문제 챌린지", description = "AI 문제 챌린지 관련 API")
public class ChallengeController {
    private final ChallengeService challengeService;

    @Operation(
            summary = "오늘의 챌린지 문제 조회",
            description = "오늘의 챌린지 문제를 조회합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 문제 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public ResponseEntity<List<ProblemsSelectResponseDto>> selectChallengeProblems() {
        return ResponseEntity.ok(challengeService.selectChallengeProblems());
    }

    @Operation(
            summary = "문제 풀이 제출",
            description = "문제를 풀이하고 제출합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "문제 풀이 제출 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping
    public ResponseEntity<?> insertProblemSolving(@Valid @RequestBody ProblemSolvingInsertRequestDto requestDto) {
        challengeService.insertProblemSolving(requestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "챌린지 랭킹 조회",
            description = "오늘의 챌린지 랭킹을 조회합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 랭킹 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/rank")
    public ResponseEntity<?> selectChallengeRankByDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(challengeService.selectChallengeRankByDate(date));
    }

    @Operation(
            summary = "챌린지 복습 리스트 조회",
            description = "챌린지 복습 리스트를 조회합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 복습 리스트 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/review")
    public ResponseEntity<List<ChallengeReviewSelectResponseDto>> selectChallengeReviewList() {
        return ResponseEntity.ok(challengeService.selectChallengeReviewList());
    }


    @Operation(
            summary = "챌린지 복습 상세",
            description = "선택한 챌린 복습 문제를 조회합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 복습 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/review/{memberId}/{subject}")
    public ResponseEntity<ProblemsSelectResponseDto> selectChallengeReviewProblem(@PathVariable ("memberId") Long memberId,
                                                                                  @PathVariable("subject") String subject) {
        return ResponseEntity.ok(challengeService.selectChallengeReviewProblem(memberId, subject));
    }
}
