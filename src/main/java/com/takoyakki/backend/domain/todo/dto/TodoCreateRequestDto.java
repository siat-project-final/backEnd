package com.takoyakki.backend.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "할 일 생성 요청 DTO")
public class TodoCreateRequestDto {

    @NotBlank(message = "사용자 ID는 필수입니다.")
    @Schema(description = "사용자 ID", example = "member123", required = true)
    private String memberId;

    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    @Schema(description = "할 일 내용", example = "스터디 자료 정리", required = true)
    private String content;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 YYYY-MM-DD이어야 합니다.")
    @Schema(description = "할 일 날짜 (yyyy-MM-dd)", example = "2025-06-18", required = true)
    private String date;

    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "시간 형식은 HH:mm이어야 합니다.")
    @Schema(description = "할 일 시간 (HH:mm)", example = "14:30")
    private String time;

    @Schema(description = "할 일 중요 여부", example = "true")
    private boolean isImportant;

    private Long id; // 생성 후 매퍼에서 세팅될 ID

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
