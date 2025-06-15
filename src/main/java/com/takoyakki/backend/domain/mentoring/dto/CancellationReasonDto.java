package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//예약취소
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationReasonDto {
    private Long reservationId;
    private String reason;
}
