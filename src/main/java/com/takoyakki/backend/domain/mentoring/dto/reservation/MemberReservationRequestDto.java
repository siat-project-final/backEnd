package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberReservationRequestDto {
    private Long mentorId; // 멘토 ID
    private Long memberId;
    private LocalDateTime date; // 예약 시간 (YYYY-MM-DD HH:mm)
    private String subject; // 멘토링 주제

    private String introduction; // 자기소개
    private String etcMessage; // 기타
}

