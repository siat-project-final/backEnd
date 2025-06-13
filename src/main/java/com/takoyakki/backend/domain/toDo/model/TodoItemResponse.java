package com.takoyakki.backend.domain.toDo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoItemResponse {
    
    private Long id;
    private Long todoDateId;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
