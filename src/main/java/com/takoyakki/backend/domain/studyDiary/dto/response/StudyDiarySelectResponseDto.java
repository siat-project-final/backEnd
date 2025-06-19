package com.takoyakki.backend.domain.studyDiary.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "학습 일지 조회 응답")
@Builder
public class StudyDiarySelectResponseDto {

    @Schema(description = "제목", example = "Java")
    private String title;
}
