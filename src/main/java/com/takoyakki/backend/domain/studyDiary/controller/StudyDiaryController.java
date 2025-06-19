package com.takoyakki.backend.domain.studyDiary.controller;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryAISummaryRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiaryAISummaryResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.service.StudyDiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/study-diary")
@Tag(name = "학습 일지", description = "학습 일지 관련 API")
public class StudyDiaryController {
    private final StudyDiaryService studyDiaryService;

    @Operation(
            summary = "학습 일지 AI 요약",
            description = "학습 일지 작성시 AI 요약 항목을 출력합니다",
            tags = {"studyDiary"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "학습 일지 AI 요약 츌룍 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/AISummary")
    public ResponseEntity<StudyDiaryAISummaryResponseDto> getAISummary(@Valid @RequestBody StudyDiaryAISummaryRequestDto requestDto) {
        return ResponseEntity.ok(studyDiaryService.getAISummary(requestDto));
    }

    @Operation(
            summary = "학습 일지 제출",
            description = "학습 일지를 제출합니다",
            tags = {"studyDiary"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "학습 일지 제출 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping
    public ResponseEntity<?> insertStudyDiary(@Valid @RequestBody StudyDiaryInsertRequestDto requestDto) {
        studyDiaryService.insertStudyDiary(requestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "학습 일지 단건 조회",
            description = "특정 학습 일지를 조회합니다",
            tags = {"studyDiary"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "학습 일지 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{diaryId}")
    public ResponseEntity<StudyDiarySelectResponseDto> selectStudyDiary(@PathVariable Long diaryId) {
        return ResponseEntity.ok(studyDiaryService.selectStudyDiary(diaryId));
    }


    @Operation(
            summary = "특정 회원의 학습 일지 목록 조회",
            description = "특정 회원의 학습 일지 목록을 조회합니다",
            tags = {"studyDiary"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "학습 일지 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<StudyDiarySelectResponseDto>> selectStudyDiaryList(@PathVariable Long memberId) {
        return ResponseEntity.ok(studyDiaryService.selectStudyDiaryList(memberId));
    }


    @Operation(
            summary = "학습 일지 수정",
            description = "학습 일지를 수정합니다",
            tags = {"studyDiary"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "학습 일지 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/{diaryId}")
    public ResponseEntity<?> updateStudyDiary(@PathVariable Long diaryId, @Valid @RequestBody StudyDiaryUpdateRequestDto requestDto) {
        studyDiaryService.updateStudyDiary(diaryId, requestDto);
        return ResponseEntity.ok().build();
    }
}

