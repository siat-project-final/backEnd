package com.takoyakki.backend.domain.mentoring.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
//멘토링 예약 상태를 멘티 , 멘토에게 보여줄때,
//얘약 목록 조회용

@Data

public class MentoringReservationResponseDto {

    @Schema(description = "예약 ID", example ="1001")
    private Long reservationId;

    @Schema(description = "멘토 이름", example = "이수현")
    private String mentorName;

    @Schema(description = "멘토 프로필 이미지 URL", example = "https://example.com/mentor.jpg")
    private String mentorImageUrl;

    @Schema(description = "예약된 날짜 및 시간 (YYYY-MM-DD HH:mm)", example = "2023-10-01 14:00")
    private LocalDateTime date;

    @Schema(description = "예약 상태", example = "waiting")
    private String status; // 예약 상태 (예: WAITING ,ACCEPT , Rejected, CANCELED)

    @Schema(description = "오픈채팅 URL(수락한 경우에만 제공)", example = "https://open.kakao.com/o/g1a2b3c4")
    private String openChatUrl; // 오픈채팅 URL
}

