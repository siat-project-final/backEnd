package com.takoyakki.backend.domain.toDo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor; // Lombok AllArgsConstructor 임포트
import lombok.Data; // Lombok Data (Getter, Setter 등) 임포트
import lombok.NoArgsConstructor; // Lombok NoArgsConstructor 임포트

import java.util.List; // List 타입을 사용하기 위해 임포트

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용
@NoArgsConstructor // 인자 없는 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Schema(description = "할 일 삭제 요청 데이터 전송 객체 (여러 개 삭제 시 사용)")
public class TodoDeleteRequestDto {
    @Schema(description = "삭제할 할 일 항목들의 ID 목록", example = "[1, 2, 3]", required = true)
    private List<Long> todoIds; // 여러 개의 todoId를 받을 수 있도록 List 형태로 정의
}