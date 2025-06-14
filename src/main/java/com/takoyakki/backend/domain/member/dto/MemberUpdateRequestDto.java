package com.takoyakki.backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "회원 정보 수정")
@Builder
public class MemberUpdateRequestDto {
    @Schema(description = "member_id")
    private Long memberId;

    @NotBlank
    @Schema(description = "email")
    private String email;
    @Schema(description = "nickname")
    private String nickname;
    @NotBlank
    @Schema(description = "phone_number")
    private String phoneNumber;
}
