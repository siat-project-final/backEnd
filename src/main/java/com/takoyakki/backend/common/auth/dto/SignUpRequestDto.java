package com.takoyakki.backend.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Schema(description = "회원가입 요청")
public class SignUpRequestDto {
    @NotBlank
    @Schema(description = "id")
    private String id;

    @NotBlank
    @Schema(description = "password")
    private String password;

    @NotBlank
    @Schema(description = "member_name")
    private String memberName;

    @NotBlank
    @Schema(description = "phone")
    private String phone;

    @Schema(description = "email")
    private String email;

    @NotBlank
    @Schema(description = "nickname")
    private String nickname;
}
