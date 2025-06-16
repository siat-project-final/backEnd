package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

/**
 * 멘토링 예약을 취소할 때 사용하는 요청 DTO.
 * - 멘티 또는 멘토가 예약 상태를 '취소'로 변경할 때 사용됨
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentoringReservationCancelRequestDto {

    @NotBlank(message = "취소 사유 코드는 필수입니다.")
    private String cancelCode; // 취소 사유 코드 (예: NO_TIME, PERSONAL_REASON 등)

    @NotBlank(message = "취소 사유 메시지는 필수입니다.")
    private String cancelMessage;



}


