package com.takoyakki.backend.domain.mentoring.model;

import lombok.Data;

// 멘토링 신청 사용하는 객체 - 신청서
@Data
public class Mentoring {
    private Long id;
    private Long mentorId;
    private Long menteeId;
    private String status; // 예약대기, 예약확정, 예약취소
    private String date;
    private String time;
    private String cancelReason; // 예약취소사유
}
