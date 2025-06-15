package com.takoyakki.backend.domain.mentoring.model;

import lombok.Data;

@Data
public class MentoringReservation {

    private Long id;

    private String title;        // 예약 제목
    private String content;      // 예약내용 or 요청사항
    private String preConversation; // 사전 대화 내용

    private String date;         // YYYY-MM-DD
    private String time;         // HH:mm

    private String status;       // (WAITING/CONFIRMED/CANCELED 등)
    private String cancelReason;

    private Long mentorId;
    private Long menteeId;

}
