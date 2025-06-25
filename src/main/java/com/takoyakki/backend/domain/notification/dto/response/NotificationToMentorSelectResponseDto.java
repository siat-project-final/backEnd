package com.takoyakki.backend.domain.notification.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "멘토 알림 응답 DTO")
@Builder
public class NotificationToMentorSelectResponseDto {
    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "타입", example = "CHALLENGE")
    private String type;

    @Schema(description = "제목", example = "챌린지 랭크 보상이 지급되었습니다.")
    private String title;

    @Schema(description = "내용", example = "데일리 챌린지 결과 랭크에 따라 포인트가 지급되었습니다.")
    private String contents;

}
