package com.takoyakki.backend.domain.toDo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data // Getter, Setter, ToString 등 자동 생성
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Schema(description = "할 일 조회 응답 데이터 전송 객체")
public class TodoSelectResponseDto {

    @Schema(description = "할 일 항목의 고유 ID", example = "1")
    private Long todoId;

    @Schema(description = "할 일을 생성한 멤버의 ID", example = "1")
    private Long memberId;

    @Schema(description = "할 일 내용", example = "운동하기")
    private String contents;

    @Schema(description = "할 일 날짜 및 시간", example = "2025-06-20T10:00:00")
    private LocalDateTime date;

    @Schema(description = "할 일 생성 시각", example = "2025-06-19T09:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "할 일 최종 업데이트 시각", example = "2025-06-19T10:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "할 일 완료 여부", example = "false")
    private Boolean isChecked;

    // TodoDto를 받아서 TodoSelectResponseDto로 변환하는 생성자
    public TodoSelectResponseDto(TodoDto todoDto) {
        this.todoId = todoDto.getTodoId();
        this.memberId = todoDto.getMemberId();
        this.contents = todoDto.getContents();
        this.date = todoDto.getDate();
        this.createdAt = todoDto.getCreatedAt();
        this.updatedAt = todoDto.getUpdatedAt();
        this.isChecked = todoDto.getIsChecked();
        // isDeleted 필드는 클라이언트에 노출하지 않으므로 포함하지 않음
    }
}