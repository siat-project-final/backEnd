package com.takoyakki.backend.domain.toDo.dto;

import java.time.LocalDateTime; // date 컬럼이 TIMESTAMP이므로 LocalDateTime 사용
// import java.time.LocalDate; // 이제 필요 없음

public class TodoItemRequestDto {
    
    private Long memberId;      // 어떤 멤버의 할 일인지
    private String contents;    // 할 일 내용 (이전 title)
    private LocalDateTime date; // 할 일 날짜 (이전 dateValue)
    private Boolean isChecked;  // 완료 여부 (이전 completed)

    // Getter와 Setter
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Boolean getIsChecked() { return isChecked; }
    public void setIsChecked(Boolean isChecked) { this.isChecked = isChecked; }

    @Override
    public String toString() {
        return "TodoRequestDto{" +
               "memberId=" + memberId +
               ", contents='" + contents + '\'' +
               ", date=" + date +
               ", isChecked=" + isChecked +
               '}';
    }
}