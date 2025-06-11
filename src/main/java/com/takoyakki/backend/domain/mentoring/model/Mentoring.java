package com.takoyakki.backend.domain.mentoring.model;

@Data
public class Mentoring {
    private Long id;
    private Long mentorId;
    private Long menteeId;
    private String status; // 예약 대기, 예약 확정, 예약 취소
    private String date;
    private String time;
    private String cancelReason; // 예약 취소 사유
}
