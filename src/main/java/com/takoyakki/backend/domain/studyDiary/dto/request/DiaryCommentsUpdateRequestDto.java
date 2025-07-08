package com.takoyakki.backend.domain.studyDiary.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryCommentsUpdateRequestDto {
    private Long memberId;
    private String contents;
}

