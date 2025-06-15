package com.takoyakki.backend.domain.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "문제 등록 요청")
@Builder
public class ProblemsInsertRequestDto {
    @Schema(description = "문제 제목", example = "Java : 난이도 1")
    private String title;

    @Schema(description = "문제 내용", example = "Java에서 정수형 데이터 타입이 아닌 것은?")
    private String contents;

    @Schema(description = "난이도 (1-5)", example = "3")
    private Integer difficulty;

    @Schema(description = "과목", example = "Java")
    private String subject;

    @Schema(description = "정답 번호", example = "2")
    private Integer correctAnswer;
}
