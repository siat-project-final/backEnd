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

    @Schema(description = "할 일 ID", example = "1")
    private Long id;

    @NotBlank(message = "사용자 ID는 필수입니다.")
    @Schema(description = "사용자 ID", example = "123")
    private String memberId;

    @NotBlank(message = "내용은 필수입니다.")
    @Schema(description = "할 일 내용", example = "스터디 준비")
    private String contents;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 yyyy-MM-dd 입니다.")
    @Schema(description = "날짜", example = "2025-06-19")
    private String date;

    @Schema(description = "중요 여부", example = "true")
    private boolean isImportant;

    public Long getId() {
        return id;
    }
}