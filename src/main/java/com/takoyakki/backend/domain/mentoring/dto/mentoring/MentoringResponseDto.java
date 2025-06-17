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
    private String mentorId;          // 멘토 이름
    private String openChatUrl;  // 멘토 오픈채팅 URL
    private LocalDateTime createdAt;    //  멘토링 생성 날짜 (YYYY-MM-DD HH:mm)
    private String mentorImageUrl;   // 멘토 프로필 이미지 URL
    private String status; // 멘토링 상태 (예: SCHEDULED, COMPLETED, CANCELED)


}
