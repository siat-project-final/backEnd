package com.takoyakki.backend.domain.member.model;

import java.time.LocalDateTime;

public class Member {
    private Long memberId;
    private String id; // 로그인 ID
    private String password;
    private String memberName;
    private String email;
    private String phone;
    private String nickname;
    private String role;
    private String status;
    private Integer totalXp;
    private Integer usablePoints;
    private Integer currentLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    // 기본 생성자 (필수)
    public Member() {
    }

    // Getter와 Setter (모든 필드에 대해)
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getTotalXp() { return totalXp; }
    public void setTotalXp(Integer totalXp) { this.totalXp = totalXp; }

    public Integer getUsablePoints() { return usablePoints; }
    public void setUsablePoints(Integer usablePoints) { this.usablePoints = usablePoints; }

    public Integer getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(Integer currentLevel) { this.currentLevel = currentLevel; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    @Override
    public String toString() {
        return "Member{" +
               "memberId=" + memberId +
               ", id='" + id + '\'' +
               ", memberName='" + memberName + '\'' +
               // ... (모든 필드를 포함하도록 필요에 따라 확장)
               '}';
    }
}