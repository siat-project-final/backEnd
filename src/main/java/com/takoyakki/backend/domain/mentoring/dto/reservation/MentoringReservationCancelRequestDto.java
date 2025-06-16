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
    private String reasonCode; // 예약 취소 사유 코드
    private String reasonMessage; // 예약 취소 사유 메시지
}
