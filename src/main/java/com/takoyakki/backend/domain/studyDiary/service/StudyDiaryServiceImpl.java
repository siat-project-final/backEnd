package com.takoyakki.backend.domain.studyDiary.service;

import com.takoyakki.backend.domain.studyDiary.api.SummaryAnthropicClient;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryAISummaryRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiaryAISummaryResponseDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.repository.StudyDiraryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyDiaryServiceImpl implements StudyDiaryService{
    private final StudyDiraryMapper studyDiraryMapper;
    private final SummaryAnthropicClient summaryAnthropicClient;

    @Override
    public int insertStudyDiary(StudyDiaryInsertRequestDto requestDto) {
        try {
            return studyDiraryMapper.insertStudyDiary(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("학습 일지 작성 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudyDiarySelectResponseDto> selectStudyDiaryList(Long memberId) {
        try {
            return studyDiraryMapper.selectStudyDiaryList(memberId);
        } catch (Exception e) {
            throw new RuntimeException("학습 일지 전체 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public int updateStudyDiary(Long id, StudyDiaryUpdateRequestDto requestDto) {
        try {
            return studyDiraryMapper.updateStudyDiary(id, requestDto);
        } catch (Exception e) {
            throw new RuntimeException("학습 일지 수정 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public StudyDiarySelectResponseDto selectStudyDiary(Long diaryId) {
        try {
            return studyDiraryMapper.selectStudyDiary(diaryId);
        } catch (Exception e) {
            throw new RuntimeException("학습 일지 단건 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Override
    public StudyDiaryAISummaryResponseDto getAISummary(StudyDiaryAISummaryRequestDto requestDto) {
        try {
            return StudyDiaryAISummaryResponseDto.builder()
                    .result(summaryAnthropicClient.createSummary(requestDto.getText()))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("학습 일지 AI 요약 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }


    @Override
    public List<StudyDiarySelectResponseDto> getStudyDiariesByMemberId(Long memberId) {
        return studyDiraryMapper.selectStudyDiariesByMemberId(memberId);
    }

    @Override
    public StudyDiarySelectResponseDto getStudyDiaryById(Long diaryId) {
        StudyDiarySelectResponseDto result = studyDiraryMapper.selectStudyDiaryById(diaryId);
        if (result == null) {
            throw new RuntimeException("해당 일지를 찾을 수 없습니다. diaryId=" + diaryId);
        }
        return result;
    }

}
