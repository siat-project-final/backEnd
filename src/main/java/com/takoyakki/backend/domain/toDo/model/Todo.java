package com.takoyakki.backend.domain.toDo.model;

import java.time.LocalDateTime;


public class Todo {
    private Long todoId;       // todo_id와 매핑
    private Long memberId;     // member_id와 매핑
    private String contents;   // contents와 매핑
    private LocalDateTime date; // date (TIMESTAMP)와 매핑
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isChecked; // is_checked와 매핑
    private boolean isDeleted; // is_deleted와 매핑


    public Todo() {
    }

    public Todo(Long todoId, Long memberId, String contents, LocalDateTime date, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isChecked, boolean isDeleted) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.contents = contents;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isChecked = isChecked;
        this.isDeleted = isDeleted;
    }

    public Long getTodoId() { return todoId; }
    public void setTodoId(Long todoId) { this.todoId = todoId; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    public Boolean getIsChecked() { // ★ 반환 타입 Boolean
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) { // ★ 파라미터 타입 Boolean
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "Todo{" +
               "todoId=" + todoId +
               ", memberId=" + memberId +
               ", contents='" + contents + '\'' +
               ", date=" + date +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               ", isChecked=" + isChecked +
               ", isDeleted=" + isDeleted +
               '}';
    }
}