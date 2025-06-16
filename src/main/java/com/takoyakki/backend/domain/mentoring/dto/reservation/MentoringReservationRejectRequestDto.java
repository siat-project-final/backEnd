package com.takoyakki.backend.domain.mentoring.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MentoringReservationRejectRequestDto {

    @Schema(description = "예약 거절 사유 코드", example = "NO_TIME")
    private String reasonCode;

    @Schema(description = "기타 사유 상세 입력", example = "개인 일정으로 불가합니다.")
    private String reasonMessage; // 예약 거절 사유 메시지


}
