package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentoringResponseDto {

    private Long reservationId;         // 예약 ID
    private Long mentorId;              // 멘토 ID
    private String mentorName;          // 멘토 이름
    private String mentorCompany;       // 멘토 회사
    private String mentorPosition;      // 멘토 직무
    private String mentoringDate;       // 예약된 멘토링 날짜 (YYYY-MM-DD)
    private String[] topics;            // 선택한 대화 주제
    private String openChatLink;        // 오픈채팅 링크 (예약 확정 시 제공)

}
