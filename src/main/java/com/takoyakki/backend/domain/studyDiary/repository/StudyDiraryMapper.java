package com.takoyakki.backend.domain.studyDiary.repository;

import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyDiraryMapper {
    int insertStudyDiary(StudyDiaryInsertRequestDto requestDto);

    StudyDiarySelectResponseDto selectStudyDiary(Long diaryId);

    int updateStudyDiary(Long id, StudyDiaryUpdateRequestDto requestDto);

    List<StudyDiarySelectResponseDto> selectStudyDiaryList(Long memberId);
}
