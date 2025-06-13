package com.takoyakki.backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberResponseDto {
    @Schema(description = "member_id")
    private Long memberId;

    @Schema(description = "id")
    private String id;
    @Schema(description = "password")
    private String password;
    @Schema(description = "role")
    private String role;
    @Schema(description = "member_name")
    private String memberName;
}
