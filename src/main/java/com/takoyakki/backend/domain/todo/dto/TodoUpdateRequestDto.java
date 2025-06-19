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

    @NotBlank(message = "내용은 필수입니다.")
    @Schema(description = "할 일 내용")
    private String content;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 yyyy-MM-dd 입니다.")
    @Schema(description = "날짜")
    private String date;

    @Schema(description = "중요 여부")
    private boolean isImportant;
}