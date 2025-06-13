package com.takoyakki.backend.domain.toDo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TodoItem {
    private Long id;
    private Long todoDateId;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
