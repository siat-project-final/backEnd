package com.takoyakki.backend.domain.mentoring.dto.mentoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//멘토링 상세 조회 시 응답으로 전달할 데이터 객체
// 멘토링 예약 상세 정보 DTO


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentoringResponseDto {

    private Long mentoringId;         // 멘토링 ID
    private String mentorName;          // 멘토 이름
    private String openChatUrl;  // 멘토 오픈채팅 URL
    private LocalDateTime scheduledAt;    // 예약된 멘토링 시간 (YYYY-MM-DD HH:mm)
    private String mentorImageUrl;   // 멘토 프로필 이미지 URL
    private String status; // 멘토링 상태 (예: SCHEDULED, COMPLETED, CANCELED)


    //선택한 대화주제 등 제거 처리.
}
