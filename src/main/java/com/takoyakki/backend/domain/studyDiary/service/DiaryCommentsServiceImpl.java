package com.takoyakki.backend.domain.studyDiary.service;

import com.takoyakki.backend.domain.studyDiary.api.SummaryAnthropicClient;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryAISummaryRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.DiaryCommentsSelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiaryAISummaryResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectPublicListResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.repository.DiaryCommentMapper;
import com.takoyakki.backend.domain.studyDiary.repository.StudyDiraryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryCommentsServiceImpl implements DiaryCommentsService{
    private final DiaryCommentMapper diaryCommentMapper;


    @Override
    public List<DiaryCommentsSelectResponseDto> selectDiaryComments(Long diaryId) {
        return diaryCommentMapper.selectDiaryComments(diaryId);
    }
}
