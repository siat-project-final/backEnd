package com.takoyakki.backend.domain.studyDiary.service;

import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyDiaryImpl implements StudyDiaryService{
    @Override
    public int insertStudyDiary(StudyDiaryInsertRequestDto requestDto) {
        return 0;
    }

    @Override
    public List<StudyDiarySelectResponseDto> selectStudyDiaryList(Long memberId) {
        return List.of();
    }

    @Override
    public int updateStudyDiary(Long id, StudyDiaryUpdateRequestDto requestDto) {
        return 0;
    }

    @Override
    public StudyDiarySelectResponseDto selectStudyDiary(Long diaryId) {
        return null;
    }
}
