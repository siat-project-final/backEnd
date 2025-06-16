package com.takoyakki.backend.domain.toDo.dto;

import lombok.AllArgsConstructor; // Lombok AllArgsConstructor 임포트
import lombok.Builder; // Lombok Builder 임포트 (Builder 패턴 사용 시)
import lombok.Data; // Lombok Data (Getter, Setter 등) 임포트
import lombok.NoArgsConstructor; // Lombok NoArgsConstructor 임포트

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용
@NoArgsConstructor // 인자 없는 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder // Builder 패턴을 사용하여 객체 생성 가능하게 함 (선택 사항이지만 유용)
public class TodoDto {
    private Long todoId;
    private Long memberId;
    private String contents;
    private LocalDateTime date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isChecked;
    private Boolean isDeleted; // 논리적 삭제를 위한 필드 (DB 테이블 컬럼과 일치해야 함)
}