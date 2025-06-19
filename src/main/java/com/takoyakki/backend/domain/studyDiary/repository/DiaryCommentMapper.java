package com.takoyakki.backend.domain.studyDiary.repository;

import com.takoyakki.backend.domain.studyDiary.dto.response.DiaryCommentsSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryCommentMapper {

    List<DiaryCommentsSelectResponseDto> selectDiaryComments(Long diaryId);
}
