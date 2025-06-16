package com.takoyakki.backend.domain.toDo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor; // Lombok AllArgsConstructor 임포트
import lombok.Data; // Lombok Data (Getter, Setter 등) 임포트
import lombok.NoArgsConstructor; // Lombok NoArgsConstructor 임포트

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용
@NoArgsConstructor // 인자 없는 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Schema(description = "할 일 생성 요청 데이터 전송 객체")
public class TodoCreateRequestDto {
    @Schema(description = "할 일을 생성할 멤버의 ID", example = "1", required = true)
    private Long memberId;

    @Schema(description = "할 일 내용", example = "운동하기", required = true)
    private String contents;

    @Schema(description = "할 일 날짜 및 시간", example = "2025-06-20T10:00:00", required = true)
    private LocalDateTime date;

    // isChecked, todoId, createdAt, updatedAt, isDeleted 필드는 생성 시 서버에서 처리되므로 요청 DTO에 포함하지 않습니다.
}