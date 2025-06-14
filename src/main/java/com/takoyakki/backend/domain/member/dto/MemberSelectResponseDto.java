package com.takoyakki.backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberSelectResponseDto {
    @Schema(description = "member_id")
    private Long memberId;

    @Schema(description = "id")
    private String id;
    @Schema(description = "role")
    private String role;
    @Schema(description = "member_name")
    private String memberName;
    @Schema(description = "email")
    private String email;
    @Schema(description = "nickname")
    private String nickname;
    @Schema(description = "phone_number")
    private String phoneNumber;
}
