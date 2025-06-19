package com.takoyakki.backend.domain.calendar.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "캘린더 일정 전체 조회 응답")
@Builder
public class CalendarScheduleListResponseDto {

    @Schema(description = "커리큘럼 과목", example = "JAVA")
    private String subject;

    @Schema(description = "학습 일지 제목", example = "JAVA 학습일지")
    private String studyDiaryTitle;

    @Schema(description = "멘토링", example = "멘토 홍길동, 주제 JAVA 기초")
    private String mentoring;


}
