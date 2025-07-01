package com.takoyakki.backend.domain.studyDiary.service;

import com.takoyakki.backend.domain.studyDiary.dto.request.DiaryCommentsInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryAISummaryRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.DiaryCommentsSelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiaryAISummaryResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectPublicListResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface DiaryCommentsService {

    List<DiaryCommentsSelectResponseDto> selectDiaryComments(Long diaryId);

    int insertDiaryComment(@Valid DiaryCommentsInsertRequestDto requestDto);

}
