package com.takoyakki.backend.domain.mentoring.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
//멘토링 예약 상태를 멘티 , 멘토에게 보여줄때,
//얘약 목록 조회용

@Data

public class MentoringReservationResponseDto {

    private Long reservationId;
    private String mentorName;
    private String mentorImageUrl;
    private String openChatUrl;
    private String subject;
    private String status;
    private LocalDateTime date;
    private String memberName;

}

