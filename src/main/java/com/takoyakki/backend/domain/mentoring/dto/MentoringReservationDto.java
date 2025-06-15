package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringReservationDto {
    private Long reservationId;
    private Long mentorId;
    private String mentorName;
    private LocalDateTime reservationDateTime;
    private String status;           // 예약 상태 (대기중, 확정 등)
}
