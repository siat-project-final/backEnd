package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//예약취소
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentoringReservationCancelRequestDto {
    private Long reservationId;
    private String cancel_reason;
}
