package com.takoyakki.backend.domain.toDo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoItemRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;
    private boolean completed;
}
