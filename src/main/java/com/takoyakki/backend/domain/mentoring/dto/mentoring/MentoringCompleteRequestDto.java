package com.takoyakki.backend.domain.mentoring.dto.mentoring;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//멘토링 완료 요청 시 전달할 데이터 객체 (예: 멘토링 ID, 완료 여부 등)

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringCompleteRequestDto {
    private Long reservationId; // 예약 ID
    private String mentorName; // 멘토 이름
    private String mentorImageUrl; // 멘토 프로필 이미지 URL
    private LocalDate reservedDate; // 예약 날짜 (YYYY-MM-DD)
    private LocalTime reservedTime; // 예약 시간 (HH:mm)
    private List<String> topics; // 멘토링 주제 목록
    private String mentoringSummary; // 멘토링 완료여부 ????



}
