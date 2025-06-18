package com.takoyakki.backend.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "할 일 수정 요청 DTO")
public class TodoUpdateRequestDto {

    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    @Schema(description = "할 일 내용", example = "회의 자료 준비")
    private String content;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 YYYY-MM-DD이어야 합니다.")
    @Schema(description = "할 일 날짜 (yyyy-MM-dd)", example = "2025-06-18")
    private String date;

    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "시간 형식은 HH:mm이어야 합니다.")
    @Schema(description = "할 일 시간 (HH:mm)", example = "15:30")
    private String time;

    @Schema(description = "할 일 중요 여부", example = "false")
    private boolean isImportant;
}
