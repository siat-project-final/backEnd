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

    private Long mentoringReservationId;               // 예약 ID
    private String mentorId;                // 멘토 이름
    private String menteeId;                //멘티 이름
    private LocalDate createdAt;           // 멘토링 완료된 날짜
}
