package com.takoyakki.backend.domain.mentoring.dto.mentoring;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 멘토링 완료 요청 시 사용하는 DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringCompleteRequestDto {

    private Long reservationId;               // 예약 ID
    private String mentorName;                // 멘토 이름
    private String mentorImageUrl;            // 멘토 프로필 이미지 URL
    private LocalDate reservedDate;           // 예약 날짜
    private LocalTime reservedTime;           // 예약 시간
    private boolean isCompleted;           // 완료 여부 (필요하다면)
}
