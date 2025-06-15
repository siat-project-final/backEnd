package com.takoyakki.backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Swagger 문서화를 위해 추가

public class MemberRequestDto {
    @Schema(description = "사용자 로그인 ID", example = "testuser1", required = true)
    private String id;
    @Schema(description = "사용자 비밀번호", example = "password123!", required = true)
    private String password;
    @Schema(description = "사용자 실명", example = "홍길동", required = true)
    private String memberName;
    @Schema(description = "이메일 주소", example = "user@example.com")
    private String email;
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;
    @Schema(description = "닉네임", example = "길동이")
    private String nickname;
    // role, status 등은 관리자가 설정하거나 기본값으로 두는 경우가 많음

    // Getter와 Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}