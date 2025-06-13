package com.takoyakki.backend.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SignUpAuthCheckDto {
    @Schema(description = "member_name")
    private String memberName;
    @Schema(description = "phone_number")
    private String phoneNumber;
}
