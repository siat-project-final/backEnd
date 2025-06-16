package com.takoyakki.backend.domain.toDo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor; // Lombok AllArgsConstructor 임포트
import lombok.Data; // Lombok Data (Getter, Setter 등) 임포트
import lombok.NoArgsConstructor; // Lombok NoArgsConstructor 임포트

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용
@NoArgsConstructor // 인자 없는 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Schema(description = "할 일 업데이트 요청 데이터 전송 객체")
public class TodoUpdateRequestDto {
    @Schema(description = "업데이트할 할 일 내용", example = "운동하기 (변경)", required = false)
    private String contents;

    @Schema(description = "업데이트할 할 일 날짜 및 시간", example = "2025-06-20T11:00:00", required = false)
    private LocalDateTime date;

    @Schema(description = "할 일 완료 여부 (true/false)", example = "true", required = false)
    private Boolean isChecked;

    // todoId는 Path Variable로 전달되므로 DTO에 포함하지 않습니다.
    // createdAt, updatedAt, memberId, isDeleted 등은 업데이트 요청 시 포함하지 않습니다.
}