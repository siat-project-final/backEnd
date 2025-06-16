package com.takoyakki.backend.domain.mentoring.dto.mentoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringCompleteRequestDto {
    private Long id;                           // 예약 ID
    private Long mentorId;                   // 멘토 아이디
    private Long menteeId;                   // 멘티 아이디
    private LocalDate mentoringDate;         // 멘토링 신청 날짜 (YYYY-MM-DD)
    private LocalTime mentoringTime;         // 멘토링 신청 시간 (HH:mm)

    private String introduction;             // 자기소개
    private List<String> conversationTopics; // 선택한 대화 주제 리스트

    private String preConversation;          // 사전 대화 내용
    private String title;                    // 예약 제목
    private String content;                  // 예약 내용

    private String status;          // 예약대기, 예약확정, 예약취소
    private String date;            // YYYY-MM-DD
    private String time;            // HH:mm

}
