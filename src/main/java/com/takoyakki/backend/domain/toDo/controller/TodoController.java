package com.takoyakki.backend.domain.toDo.controller;

import com.takoyakki.backend.domain.toDo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
}
