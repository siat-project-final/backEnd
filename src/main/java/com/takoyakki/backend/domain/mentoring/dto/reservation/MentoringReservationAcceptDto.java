package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//멘토가 예약을
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringReservationAcceptDto {
    private Long id;
    private String name;
    private String company;
    private String job;
    private String career;
    private String profileImageUrl;       // 멘토 사진 URL
    private List<String> availableDates;  // ["2025-06-20", "2025-06-22"]
}
