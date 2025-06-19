package com.takoyakki.backend.domain.studyDiary.controller;

import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryAISummaryRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.DiaryCommentsSelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiaryAISummaryResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectPublicListResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.service.DiaryCommentsService;
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
@RequestMapping("/v1/study-diary/comments")
@Tag(name = "학습 일지 댓글", description = "학습 일지 댓글 관련 API")
public class DiaryCommentsController {
    private final DiaryCommentsService diaryCommentsService;

    @Operation(summary = "특정 다이어리의 댓글 전체 조회", description = "특정 다이어리의 댓글 전체를 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{diaryId}")
    public ResponseEntity<List<DiaryCommentsSelectResponseDto>> selectDiaryComments(@PathVariable Long diaryId) {
        return ResponseEntity.ok(diaryCommentsService.selectDiaryComments(diaryId));
    }

}