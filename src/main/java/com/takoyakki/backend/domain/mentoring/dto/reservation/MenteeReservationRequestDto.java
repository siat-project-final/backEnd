package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter

@NoArgsConstructor
public class MenteeReservationRequestDto {
    private Long mentorId; // 멘토 ID
    private LocalDateTime reservedAt; // 예약 시간 (YYYY-MM-DD HH:mm)

    private String introduction; // 자기소개
    private List<String> topics;
    private String etcMessage; // 기타
}
