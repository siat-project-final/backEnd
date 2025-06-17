package com.takoyakki.backend.domain.mentoring.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class MentoringReservationRejectRequestDto {

    @Schema(description = "예약 거절 사유 코드", example = "NO_TIME")
    @NotBlank(message = "거절 사유 코드는 필수입니다.")
    private String rejectReason;

    @Schema(description = "기타 사유 상세 입력", example = "개인 일정으로 불가합니다.")
    @NotBlank(message = "거절 메시지는 필수입니다.")
    private String rejectMessage;
}
