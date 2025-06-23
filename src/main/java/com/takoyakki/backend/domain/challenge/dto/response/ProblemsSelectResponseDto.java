package com.takoyakki.backend.domain.challenge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "문제 단건 조회 응답")
@Builder
public class ProblemsSelectResponseDto {
    @Schema(description = "답", example = "1")
    private int answer;

    @Schema(description = "배점", example = "1")
    private int points;

    @Schema(description = "문제", example = "Java의 기본 데이터 타입은?")
    private String contents;

    @Schema(description = "과목", example = "JAVA")
    private String subject;


}
