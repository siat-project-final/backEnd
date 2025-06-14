package com.takoyakki.backend.domain.mentoring.model;

import java.util.Date;
import lombok.Data;

//멘토링 예약 관리용 (예약 상태, 날짜, 취소 사유 포함)
@Data
public class MentoringReservation {
    private Long id;
    private String title; //제목 추가할지말지 고민 (?) 추가?해야하나
    private String content;
    private String date; // YYYY-MM-DD
    private String time; // HH:mm
    private Long mentorId;
}
