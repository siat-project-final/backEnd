package com.takoyakki.backend.domain.studyDiary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "학습 일지 수정 요청")
@Builder
public class StudyDiaryUpdateRequestDto {

    @Schema(description = "제목", example = "Java")
    private String title;
}
