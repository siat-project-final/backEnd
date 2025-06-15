package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringReservationDto {
    private Long reservationId;      // 예약 ID
    private Long mentorId;           // 멘토 ID
    private String mentorName;       // 멘토 이름 (조회용)
    private Long menteeId;           // 멘티 ID

    private String title;            // 예약 제목
    private String content;          // 예약 내용 or 요청사항
    private String preConversation;  // 사전 대화 내용

    private String date;             // YYYY-MM-DD (예약일)
    private String time;             // HH:mm (예약 시간)
    private LocalDateTime reservationDateTime; // 예약일시 (필요 시)

    private String status;           // 예약 상태 (예: WAITING, CONFIRMED, CANCELED)
    // 취소 사유 (nullable)
}
