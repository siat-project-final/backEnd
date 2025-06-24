package com.takoyakki.backend.domain.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "챌린지 포인트 보상 알림 DTO")
@Builder
public class NotificationChallengeToMenteeDto {
    @Schema(description = "제목", example = "멘토링 예약 거절")
    private String contents;

    @Schema(description = "내용", example = "멘토링 예약이 거절 되었습니다.")
    private String title;

}
